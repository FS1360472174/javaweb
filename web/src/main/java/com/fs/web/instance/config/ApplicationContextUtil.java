/*
 * ApplicationContextUtil.java
 */

package com.fs.web.instance.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * @author fangzhang
 *
 */
public class ApplicationContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(final org.springframework.context.ApplicationContext applicationContext)
            throws BeansException {
        if (ApplicationContextUtil.applicationContext == null) {
            ApplicationContextUtil.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(final String beanName) {
        return applicationContext == null ? null : applicationContext.getBean(beanName);
    }

    public static <T> Map<String, T> getBeansOfType(final Class<T> type) {
        if (applicationContext != null) {
            final ListableBeanFactory listableBeanFactory = applicationContext;
            return listableBeanFactory.getBeansOfType(type);
        }
        return null;
    }
}
