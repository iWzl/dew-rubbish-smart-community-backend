package com.upuphub.dew.community.general.api.config;

import com.upuphub.dew.community.general.api.shiro.*;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 权限认证配置
 *
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/14 21:18
 */
@Configuration
public class ShiroConfiguration {

    //权限拦截过滤器链
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
        // 初始化的权限拦截工厂
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager,Shiro的核心安全接口
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 添加自定义过滤器
        Map<String, Filter> filtersMap = new LinkedHashMap<>();
        // 添加限制同时在线的个数
        // filtersMap.put("KickOut",)
        // 添加JWT认证过滤器
        filtersMap.put("JWT", new JWTFilter());
        //配置自定义登出 覆盖 logout 之前默认的LogoutFilter
        filtersMap.put("logout",new ShiroLogoutFilter());
        // 注册拦截器到工厂中
        shiroFilterFactoryBean.setFilters(filtersMap);

        // 配置访问权限 必须是LinkedHashMap，因为它必须保证有序
        // 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 --> : 这是一个坑，一不小心代码就不好使了
        final Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //配置不登录可以访问的资源，anon 表示资源都可以匿名访问
        //配置记住我或认证通过可以访问的地址
        filterChainDefinitionMap.put("/css/api/account/login", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/doc.html","anon");

        filterChainDefinitionMap.put("/css/api/common/tools/code/*/send","anon");
        filterChainDefinitionMap.put("/css/api/common/tools/*/categories","anon");
        filterChainDefinitionMap.put("/css/api/common/tools/*/search","anon");
        filterChainDefinitionMap.put("/css/api/account/password/reset","anon");
        //logout是shiro提供的过滤器,这是走自定义的 shiroLogoutFilter 上面有配置
        //filterChainDefinitionMap.put("/logout", "logout");
        //此时访问/user/delete需要delete权限,在自定义Realm中为用户授权。
        //filterChainDefinitionMap.put("/user/delete", "perms[\"user:delete\"]");

        //其他资源都需要认证  authc 表示需要认证才能进行访问 user表示配置记住我或认证通过可以访问的地址
        //如果开启限制同一账号登录,改为 .put("/**", "kickOut,user");
        filterChainDefinitionMap.put("/css/api/account/logout","logout");
        filterChainDefinitionMap.put("/css/api/**", "JWT");

        // 添加连接信息到拦截工厂中
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    /**
     * shiro缓存管理器;
     * 需要添加到securityManager中
     * @return RedisCacheManager对象
     */
    @Bean
    public RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        //redis中针对不同用户缓存
        //用户权限信息缓存时间
        redisCacheManager.setExpire(200000L);
        redisCacheManager.setKeyPrefix("shiro:cache:");
        return redisCacheManager;
    }



    //配置核心安全事务管理器
    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        // 关闭框架自带的session
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        // 配置自定义Realm
        securityManager.setRealm(authRealm);
        securityManager.setCacheManager(cacheManager());

        return securityManager;
    }

    //RedisManager(使用Redis)
    @Bean(name = "redisManager")
    public RedisManager redisManager() {
        return new RedisManager();
    }


    //配置自定义的权限登录器
    @Bean(name = "authRealm")
    public AuthRealm authRealm() {
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCachingEnabled(true);
        // 启用身份验证缓存，即缓存AuthenticationInfo信息，默认false
        authRealm.setAuthenticationCachingEnabled(true);
        // 缓存AuthenticationInfo信息的缓存名称
        authRealm.setAuthenticationCacheName("authenticationCache");
        // 不缓存 权限信息 换成在service 层控制缓存
        authRealm.setAuthorizationCachingEnabled(false);
        //缓存AuthorizationInfo信息的缓存名称
        authRealm.setAuthorizationCacheName("authorizationCache");
        //配置自定义密码比较器
        authRealm.setCredentialsMatcher(credentialsMatcher());
        return authRealm;
    }

    // 自定义密码比较器
    @Bean("credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        CredentialsMatcher credentialsMatcher = new CredentialsMatcher();
//        // 配置Redis工具类,进行缓存操作
//        retryLimitHashedCredentialsMatcher.setRedisManager(redisManager());
//        //如果密码加密,可以打开下面配置
//        //加密算法的名称
//        retryLimitHashedCredentialsMatcher.setHashAlgorithmName("MD5");
//        //配置加密的次数
//        retryLimitHashedCredentialsMatcher.setHashIterations(0);
//        //是否存储为16进制
//        retryLimitHashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    // 配置Shiro生命周期处理器
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启shiro 注解模式
     * 可以在controller中的方法前加上注解
     * 如 @RequiresPermissions("userInfo:add")
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }

    // 开启权限的注解
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }
}