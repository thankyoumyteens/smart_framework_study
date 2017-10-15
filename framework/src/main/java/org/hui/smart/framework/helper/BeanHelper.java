package org.hui.smart.framework.helper;

import org.hui.smart.framework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Admin on 2017/10/15.
 */
public final class BeanHelper {

    // 存储类的实例
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<>();

    static {
        Set<Class<?>> classSet = ClassHelper.getBeanClassSet();
        for (Class<?> cls : classSet) {
            Object instance = ReflectionUtil.newInstance(cls);
            BEAN_MAP.put(cls, instance);
        }
    }

    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    public static void setBean(Class<?> cls, Object o) {
        BEAN_MAP.put(cls, o);
    }

    /**
     * 根据类获取对应的实例
     * @param cls
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<?> cls) {
        if (!BEAN_MAP.containsKey(cls)) {
            throw new RuntimeException("bean " + cls.getName() + " 不存在");
        }
        return (T) BEAN_MAP.get(cls);
    }
}
