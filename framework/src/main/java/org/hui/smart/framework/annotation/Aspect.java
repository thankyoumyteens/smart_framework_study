package org.hui.smart.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by Admin on 2017/10/16.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    /**
     * 存储被代理类带有的注解
     * @return
     */
    Class<? extends Annotation> value();
}
