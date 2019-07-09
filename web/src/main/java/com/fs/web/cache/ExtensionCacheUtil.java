/*
 * SummerCacheSupport.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.web.cache;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 这个类不应该存在，需要合并到CacheExtension
 */
public class ExtensionCacheUtil {

    public static class Keys {
        private final Collection<Object> keys;

        public Keys(final Collection<Object> keys) {
            this.keys = keys;
        }

        public Collection<Object> getKeys() {
            return keys;
        }
    }

    private CacheExtension cache;


    public static Object lookup(final CacheExtension cache, final Object key) {
        if (key instanceof Keys) {
            final Collection<Object> originalKeys = ((Keys) key).getKeys();
            if (originalKeys == null || originalKeys.isEmpty()) {
                return cache;
            }
            final List<Object> keys = originalKeys.stream()
                    .filter(Objects::nonNull).collect(Collectors.toList());
            final Map<Object, Object> hits = cache.getAll(keys);
            return hits;
        }
        return null;
    }


    public static boolean putCollections(final CacheExtension cache,
            final Object key, final Object value) {
        if (key instanceof Collection && value instanceof Map) {
            cache.putAll((Map<Object, Object>) value);
            return true;
        }
        return false;
    }

}
