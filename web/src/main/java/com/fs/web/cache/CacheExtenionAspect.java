/*
 * CacheExtenionAspect.java
 */

package com.fs.web.cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author fangzhang
 *
 */
@Aspect
@Component
@Order(Integer.MIN_VALUE)
public class CacheExtenionAspect {

    @Autowired
    private CacheExtensionManage cacheExtensionManage;

    /**
     * 返回的结果中缓存命中的从缓存中获取，没有命中的调用原来的方法获取
     * @param joinPoint
     * @return
     */
    @Around("@annotation(org.springframework.cache.annotation.Cacheable)")
    @SuppressWarnings("unchecked")
    public Object aroundCache(final ProceedingJoinPoint joinPoint) {
        final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        final Object result = directly(joinPoint);
        final CacheExtension.CacheResult cacheResult = (CacheExtension.CacheResult) result;
        final Method method = signature.getMethod();
        final Object target = joinPoint.getTarget();
        final Object[] args = joinPoint.getArgs();
        // 修改掉Collection值
        args[0] = cacheResult.getMiss();
        try {
            final Map<Object, Object> notHit = CollectionUtils.isEmpty(cacheResult.getMiss()) ? null
                    : (Map<Object, Object>) (method.invoke(target, args));
            final Map<Object, Object> hits = cacheResult.getHit();
            if (Objects.isNull(notHit)) {
                return hits;
            }
            // 设置缓存
            cacheResult.getCache().putAll(notHit);
            hits.putAll(notHit);
            return hits;
        } catch (final IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException(e);
        } finally {
        }
    }

    private Object directly(final ProceedingJoinPoint joinPoint) {
        return directly(joinPoint, null);
    }

    private Object directly(final ProceedingJoinPoint joinPoint, final Object[] args) {
        try {
            if (args == null) {
                return joinPoint.proceed();
            } else {
                return joinPoint.proceed(args);
            }
        } catch (final RuntimeException e) {
            throw e;
        } catch (final Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }
}