package com.upuphub.dew.community.general.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Swagger2 API 自动生成文档配置
 * API服务地址: http://项目地址/swagger-ui.html
 *
 * @author Leo Wang
 * @version 1.0.0
 * @date 2019/7/25 23:11
 */

@ComponentScan("com.upuphub.dew.community.general.api.controller")
@Configuration
@EnableSwagger2
public class Swagger2Configuration {


    /**
     * 创建BeanApi
     * apiInfo() 增加API相关信息
     * 通过select()函数,返回一个ApiSelectorBuilder实例,控制哪些接口暴露给Swagger
     * 采用指定扫描的包路径来定义指定要建立API的目录。
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.upuphub.dew.community.general.api.controller"))//扫描Api包
                .paths(PathSelectors.any())
                .build();
    }

    private List<ApiKey> securitySchemes() {
        return newArrayList(
                new ApiKey("X-Token", "X-Token", "header"));
    }

    private List<SecurityContext> securityContexts() {
        return newArrayList(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.any())
                        .build()
        );
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(
                new SecurityReference("X-Token", authorizationScopes));
    }

    /**
     * 创建该API的基本信息
     * 访问地址：http://项目实际地址/swagger-ui.html
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("DEW RBC API")
                .description("DEW RUBBISH COMMUNITY API接口")//api描述
                //.termsOfServiceUrl("https://api.itsc.cc:666/")//服务地址
                .contact(new Contact("Leo Wang", "https://www.baidu.com/", "Wangzl@Wangzl.cc"))//联系信息
                .version("beta.v1.0.0")
                .build();
    }
}
