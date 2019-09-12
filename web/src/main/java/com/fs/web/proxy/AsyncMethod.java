/*
 * AsyncMethod.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.web.proxy;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author fangzhang
 *
 */
@Component
public class AsyncMethod {

    @Autowired
    @Lazy
    private AsyncMethod asyncMethod;

    public void testAsync() {
        System.out.println(Thread.currentThread().getName());
        System.out.println("async1");
      //  testAsnc2();
        asyncMethod.testAsnc3();
      //((AsyncMethod)AopContext.currentProxy()).testAsnc2();
    }

    /**
     * 测试ASPECTJ调用
     */
    @Async
    private void testAsnc2() {
        System.out.println(Thread.currentThread().getName());
        System.out.println("async2");
    }


    /**
     * 测试autowired自调用
     */
    @Async
    private void testAsnc3() {
        System.out.println(Thread.currentThread().getName());
        System.out.println("async3");
    }
}
