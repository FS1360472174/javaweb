/*
 * ProxyTest.java
 */

package com.fs.web.proxy;

import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author fangzhang
 *
 */
public class ProxyTest{
    interface IHello{ void sayHello();
}
static class Hello implements IHello{
        @Override
        public void sayHello(){ System.out.println(" hello world");}
}
static class DynamicProxy implements InvocationHandler {
    Object originalObj;
    Object bind(Object originalObj){ this. originalObj= originalObj;
    return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(), originalObj.
            getClass(). getInterfaces(),null); }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        System.out.println(" welcome");
        return method.invoke(originalObj, args);
    }
}
public static void main(String[] args){
        IHello hello=( IHello) new DynamicProxy().bind(new Hello());
        hello.sayHello();
    }
}


