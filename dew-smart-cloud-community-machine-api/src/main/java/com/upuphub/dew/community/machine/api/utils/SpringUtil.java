package com.upuphub.dew.community.machine.api.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * SpringBean构建的工具类
 *
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/6 21:20
 */

@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    /**
     * 获取Spring容器的ApplicationContext对象
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取对象，重写了bean方法
     * @param name 类名称
     * @return Object 一个以所给名字注册的bean的实例
     * @throws BeansException bean异常
     */
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    /**
     * 通过类对象从容器中获取对象的实例
     * @param clz 类对象
     * @return T 容器中该Bean对象的实例
     * @throws BeansException bean异常
     */
    public static <T> T getBean(Class<T> clz) {
        return applicationContext.getBean(clz);
    }
}
