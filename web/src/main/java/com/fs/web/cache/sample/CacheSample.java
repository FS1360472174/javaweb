/*
 * CacheSample.java
 */

package com.fs.web.cache.sample;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/**
 * @author fangzhang
 *
 */
@Component
public class CacheSample {
    @Cacheable(cacheNames = "users")
    public Map<Long, User> getUser(Collection<Long> userIds) {
        System.out.println("not cache");
        Map<Long, User> mapUser = new HashMap<>();
        userIds.forEach(userId -> {
            mapUser.put(userId, new User());
        });
        return mapUser;
    }

    @Cacheable(cacheNames ="users2")
    public Map<Long, User> getUserV2(Collection<Long> userIds) {
        System.out.println("not cache");
        Map<Long, User> mapUser = new HashMap<>();
        userIds.forEach(userId -> {
            mapUser.put(userId, new User());
        });
        return mapUser;
    }
}
