/*
 * ApplicationAutoConfig.java
 */

package com.fs.web.instance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fangzhang
 *
 */
@Configuration
public class ApplicationAutoConfig {
    @Bean({"applicationContext"})
    public ApplicationContextUtil autoContext() {
        return new ApplicationContextUtil();
    }
}
