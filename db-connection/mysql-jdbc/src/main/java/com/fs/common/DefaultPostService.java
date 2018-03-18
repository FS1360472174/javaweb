package com.fs.common;

import javafx.geometry.Pos;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author cnstonefang@gmail.com
 */
public class DefaultPostService implements PostService{
    private final ThreadLocal<PostService> localPostService = new ThreadLocal<PostService>();
    private final PostService postServiceProxy;

    public DefaultPostService() {
        postServiceProxy = (PostService) Proxy.newProxyInstance(
                PostService.class.getClassLoader(),
                new Class[]{PostService.class},
                new PostServiceInterceptor());
    }

    private class PostServiceInterceptor implements InvocationHandler {
        public PostServiceInterceptor() {
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            final PostService postService = DefaultPostService.this.localPostService.get();
            if (postService != null) {
                try {
                    return method.invoke(postService, args);
                } catch (Throwable t) {

                }
            }
            return null;
        }
    }
}
