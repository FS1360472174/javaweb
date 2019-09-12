/*
 * ProxyTest.java
 */

package com.fs.web.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author fangzhang
 *
 */
public class JdkProxyTest {
    public static void main(final String[] args) {
        // 必须要先有对象
        final IHello hello = (IHello) new Hello();
        // 返回的类型必须是接口
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        final IHello proxy = (IHello) Proxy.newProxyInstance(IHello.class.getClassLoader(), new
                Class<?>[]
                { IHello.class }, new DynamicProxy(hello));
        proxy.sayHello();
    }

    interface IHello {
        void sayHello();
    }

    static class Hello implements IHello {
        @Override
        public void sayHello() {
            System.out.println(" hello world");
        }
    }

    static class DynamicProxy implements InvocationHandler {
        Object originalObj;

        public DynamicProxy(final Object realObj) {
            this.originalObj = realObj;
        }

        @Override
        public Object invoke(final Object proxy, final Method method, final Object[] args)
                throws Throwable {
            System.out.println(" welcome");
            return method.invoke(originalObj, args);
        }
    }
}


