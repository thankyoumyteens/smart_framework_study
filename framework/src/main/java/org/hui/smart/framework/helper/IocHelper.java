package org.hui.smart.framework.helper;

import org.hui.smart.framework.annotation.Inject;
import org.hui.smart.framework.util.ReflectionUtil;
import org.hui.smart.framework.util.ArrayUtil;
import org.hui.smart.framework.util.CollectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by Admin on 2017/10/15.
 * 实现依赖注入
 */
public final class IocHelper {

    // 注入有Inject注解的成员变量
    static {
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)) {
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                // 获取所有成员变量
                Field[] fields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(fields)) {
                    for (Field field : fields) {
                        // 判断是否有Inject注解
                        if (field.isAnnotationPresent(Inject.class)) {
                            // @Inject
                            // private HomeService homeService; 中的HomeService
                            Class<?> injectClass = field.getType();
                            // 要注入的实例
                            Object injectInstance = beanMap.get(injectClass);
                            if (injectInstance != null) {
                                // 将injectInstance注入到当前成员变量field
                                ReflectionUtil.setField(beanInstance, field, injectInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
