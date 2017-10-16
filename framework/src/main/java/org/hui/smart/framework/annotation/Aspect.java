package org.hui.smart.framework.annotation;

import java.lang.annotation.Annotation;

/**
 * Created by Admin on 2017/10/16.
 */
public @interface Aspect {

    Class<? extends Annotation> value();
}
