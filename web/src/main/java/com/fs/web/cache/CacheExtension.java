/*
 * CacheExtension.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.web.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.cache.support.NullValue;
import org.springframework.cglib.core.internal.LoadingCache;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Function;

/**
 * @author fangzhang
 * 缓存数据结构
 */
public class CacheExtension extends AbstractValueAdaptingCache {

    private final String name;
    /**
     * Create a {@link CacheExtension} instance with the specified name and the
     * given internal {@link com.github.benmanes.caffeine.cache.Cache} to use.
     * @param name the name of the cache
     * @param cache the backing Caffeine Cache instance
     */
    public CacheExtension(String name, com.github.benmanes.caffeine.cache.Cache<Object, Object> cache) {
        this(name, cache, true);
    }
    /**
     * 内存存储
     */
    private final com.github.benmanes.caffeine.cache.Cache<Object, Object> cache;

    /**
     * 支持collection
     * @param map
     */
    public void putAll(final Map<Object, Object> map) {
        if (map.isEmpty()) {
            return;
        }
        final Map<Object, Object> putMap = new HashMap<>(map.size());
        for (final Map.Entry<Object, Object> entry : map.entrySet()) {
            final Object storeValue = toStoreValue(entry.getValue());
            if (storeValue != null) {
                putMap.put(entry.getKey(), storeValue);
            }
        }
        this.cache.putAll(putMap);
    }

    /**
     * 支持collection
     * @param keys
     * @return
     */
    public Map<Object, Object> getAll(final Collection<Object> keys) {
        final Map<Object, Object> all = this.cache.getAllPresent(keys);
        final Map<Object, Object> result = new HashMap<>(all.size());
        for (final Map.Entry<Object, Object> entry : all.entrySet()) {
            if (Objects.isNull(fromStoreValue(entry.getValue()))) {
                result.put(entry.getKey(), null);
            } else {
                result.put(entry.getKey(), (entry.getValue()));
            }
        }
        return result;
    }

    /**
     * Create a {@link CacheExtension} instance with the specified name and the
     * given internal {@link com.github.benmanes.caffeine.cache.Cache} to use.
     * @param name the name of the cache
     * @param cache the backing Caffeine Cache instance
     * @param allowNullValues whether to accept and convert {@code null}
     * values for this cache
     */
    public CacheExtension(String name, com.github.benmanes.caffeine.cache.Cache<Object, Object> cache,
            boolean allowNullValues) {

        super(allowNullValues);
        Assert.notNull(name, "Name must not be null");
        Assert.notNull(cache, "Cache must not be null");
        this.name = name;
        this.cache = cache;
    }


    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final com.github.benmanes.caffeine.cache.Cache<Object, Object> getNativeCache() {
        return this.cache;
    }

    @Override
    public ValueWrapper get(Object key) {
        return super.get(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Object key, final Callable<T> valueLoader) {
        return (T) fromStoreValue(this.cache.get(key, new LoadFunction(valueLoader)));
    }

    /**
     * 获取数据，要改造
     * @param key
     * @return
     */
    @Override
    protected Object lookup(Object key) {
        // 从collection中获取
        final Object result = ExtensionCacheUtil.lookup(this, key);
        if (result != null) {
            return result;
        }
        return this.cache.getIfPresent(key);
    }

    @Override
    public void put(Object key, Object value) {
        if (ExtensionCacheUtil.putCollections(this, key, value)) {
            return;
        }
        this.cache.put(key, toStoreValue(value));
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, final Object value) {
        PutIfAbsentFunction callable = new PutIfAbsentFunction(value);
        Object result = this.cache.get(key, callable);
        return (callable.called ? null : toValueWrapper(result));
    }

    @Override
    public void evict(Object key) {
        this.cache.invalidate(key);
    }

    @Override
    public void clear() {
        this.cache.invalidateAll();
    }


    private class PutIfAbsentFunction implements Function<Object, Object> {

        private final Object value;

        private boolean called;

        public PutIfAbsentFunction(Object value) {
            this.value = value;
        }

        @Override
        public Object apply(Object key) {
            this.called = true;
            return toStoreValue(this.value);
        }
    }


    private class LoadFunction implements Function<Object, Object> {

        private final Callable<?> valueLoader;

        public LoadFunction(Callable<?> valueLoader) {
            this.valueLoader = valueLoader;
        }

        @Override
        public Object apply(Object o) {
            try {
                return toStoreValue(valueLoader.call());
            }
            catch (Exception ex) {
                throw new ValueRetrievalException(o, valueLoader, ex);
            }
        }
    }
}
