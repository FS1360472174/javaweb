/*
 * CglibProxyTest.java
 */

package com.fs.web.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author fangzhang
 *
 */
public class CglibProxyTest {

    private static <T> T getProxy(final Class<T> cls) {
        final Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cls);
        enhancer.setCallback(new Interceptor());
        return (T) enhancer.create();
    }

    public static void main(final String[] args) throws Exception {
        // 直接创建代理对象
        final Hello proxyService = getProxy(Hello.class);
        proxyService.sayHello();
    }


    static class Hello {
        public void sayHello() {
            System.out.println(" hello world");
        }
    }

    static class Interceptor implements MethodInterceptor {
        @Override
        public Object intercept(final Object o, final Method method, final Object[] objects,
                final MethodProxy methodProxy)
                throws Throwable {
            System.out.println("welcome to cglib");
            return methodProxy.invokeSuper(o, objects);
        }
    }
}
