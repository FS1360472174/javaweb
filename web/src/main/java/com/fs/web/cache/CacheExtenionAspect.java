/*
 * CacheExtenionAspect.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.web.cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * @author fangzhang
 *
 */
//@Aspect
@Component
@Order(Integer.MIN_VALUE)
public class CacheExtenionAspect {

        @Autowired
        private CacheExtensionManage cacheExtensionManage;

        @Around("@annotation(org.springframework.cache.annotation.Cacheable)")
        @SuppressWarnings("unchecked")
        public Object aroundCache(final ProceedingJoinPoint joinPoint) {
            final long start = System.nanoTime();
            final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            final Cacheable annotation = signature.getMethod().getAnnotation(Cacheable.class);
            final String[] cacheNames = annotation.cacheNames().length == 0 ?
                    annotation.value() : annotation.cacheNames();

            final Class<?> returnType = signature.getReturnType();

            final String keyGeneratorName = annotation.keyGenerator();

            //Cacheable注解需要有自定义的keyGenerator
            if (StringUtils.isEmpty(keyGeneratorName)) {
                return directly(joinPoint);
            }
            final Object result = directly(joinPoint);

            final CacheExtension cache = (CacheExtension) result;
            final Method method = signature.getMethod();
            final Object target = joinPoint.getTarget();
            final Object[] args = joinPoint.getArgs();
            final Collection<Object> keys = (Collection<Object>) args[0];

            try {
                final Map<Object, Object> rest = (Map<Object, Object>) (method.invoke(target, args));
                final Map<Object, Object> hits = new HashMap<>();
                if (Objects.isNull(rest)) {
                    return hits;
                }
                cache.putAll(rest);
                hits.putAll(rest);
                return hits;
            } catch (final IllegalAccessException | InvocationTargetException e) {
                throw new IllegalStateException(e);
            } finally {
            }
        }

        @SuppressWarnings("rawtypes")
        @Around("within (org.springframework.cache.Cache+) && " +
                "execution(* *.get(Object))")
        public Object aroundGenericGet(final ProceedingJoinPoint joinPoint) {
            final Object[] args = joinPoint.getArgs();
            final Object key = args[0];
            if (key == Optional.empty()) {
                return new SimpleValueWrapper(null);
            } else if (key instanceof Optional) {
                args[0] = ((Optional) key).get();
                return directly(joinPoint, args);
            } else {
                return directly(joinPoint);
            }
        }

        private Object directlyWithStats(
                final ProceedingJoinPoint joinPoint
        ) {
            final Object result = directly(joinPoint);
            return result;
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