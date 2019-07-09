/*
 * CacheController.java
 */

package com.fs.web.cache.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fangzhang
 *
 */
@RestController
@RequestMapping("/api/cache")
public class CacheController {
    @Autowired
    private CacheSample cacheSample;
    @GetMapping("/user")
    public List<User> getUser() {
        return cacheSample.getUser(Arrays.asList(1L,2L)).values().stream().collect(Collectors.toList());
    }

    /**
     *  应该有部分被缓存起来
     */
    @GetMapping("/user/v2")
    public List<User> getUserV2() {
        return cacheSample.getUserV2(Arrays.asList(1L,3L)).values().stream().collect(Collectors.toList());
    }
}
