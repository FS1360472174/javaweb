/*
 * CacheConfig.java
 */

package com.fs.web.cache.sample;

import com.fs.web.cache.CacheExtensionManage;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author fangzhang
 *
 */
@Configuration
public class CacheConfig {
    //@Primary
    @Bean(name = { "cacheManager" })
    public CacheManager getCache() {
      return new ConcurrentMapCacheManager("users");
    }

    @Primary
    @Bean
    public CacheManager getExtensionCache() {
        return new CacheExtensionManage("users2");
    }
}