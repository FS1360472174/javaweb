/*
 * ExtensionServiceLoader.java
 * a
 */

package com.fs.web.serviceloader;

import com.fs.web.instance.state.AbstractState;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author fangzhang
 *
 */
public class ExtensionServiceLoader<T> {
    private static final ConcurrentMap<Class<?>, ExtensionServiceLoader<?>>
            EXTENSION_LOADERS = new ConcurrentHashMap<Class<?>, ExtensionServiceLoader<?>>();
    private static final ConcurrentMap<Class<?>, Object> EXTENSION_INSTANCES = new ConcurrentHashMap<Class<?>, Object>();
    private final Class<?> type;
    private ExtensionServiceLoader(Class<?> type) {
        this.type = type;

    }
    public static <T> ExtensionServiceLoader<T> getExtensionLoader(Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("Extension type == null");
        }

        ExtensionServiceLoader<T> loader = (ExtensionServiceLoader<T>) EXTENSION_LOADERS.get(type);
        if (loader == null) {
            EXTENSION_LOADERS.putIfAbsent(type, new ExtensionServiceLoader<T>(type));
            loader = (ExtensionServiceLoader<T>) EXTENSION_LOADERS.get(type);
        }
        return loader;
    }

    /**
     * Find the extension with the given name. If the specified name is not found, then {@link IllegalStateException}
     * will be thrown.
     */
    @SuppressWarnings("unchecked")
    public T getExtension(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Extension name == null");
        }


        Object instance = createExtension(name);

        return (T) instance;
    }
    private T createExtension(String name) {
        Class<?> clazz = getExtensionClasses().get(name);
        if (clazz == null) {

        }
        try {
            T instance = (T) EXTENSION_INSTANCES.get(clazz);
            if (instance == null) {
                EXTENSION_INSTANCES.putIfAbsent(clazz, (T) clazz.newInstance());
                instance = (T) EXTENSION_INSTANCES.get(clazz);
            }
            injectExtension(instance);
            return instance;
        } catch (Throwable t) {
            throw new IllegalStateException("Extension instance(name: " + name + ", class: " +
                    type + ")  could not be instantiated: " + t.getMessage(), t);
        }
    }

    private Map<String, Class<?>> getExtensionClasses() {
        Map<String, Class<?>> classes = loadExtensionClasses();
        return classes;
    }

    private Map<String, Class<?>> loadExtensionClasses() {
        ServiceLoader<?> classes = ServiceLoader.load(type);
        final Map<String, Class<?>> result = new HashMap<>();
        for(Object c : classes) {
            result.put(c.getClass().getName(), c.getClass());
        }
        return result;
    }

    private T injectExtension(T instance) {
        try {
                for (Method method : instance.getClass().getMethods()) {
                    if (method.getName().startsWith("set")
                            && method.getParameterTypes().length == 1
                            && Modifier.isPublic(method.getModifiers())) {
                        Class<?> pt = method.getParameterTypes()[0];
                        try {
                            String property = method.getName().length() > 3 ? method.getName().substring(3, 4).toLowerCase() + method.getName().substring(4) : "";
                            Object object = getExtensionLoader(pt).getExtension(property);
                            if (object != null) {
                                method.invoke(instance, object);
                            }
                        } catch (Exception e) {
                        }
                    }
                }
        } catch (Exception e) {

        }
        return instance;
    }

}
