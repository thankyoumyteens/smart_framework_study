package org.hui.use.proxy;

import org.hui.smart.framework.annotation.Aspect;
import org.hui.smart.framework.annotation.Controller;
import org.hui.smart.framework.proxy.AspectProxy;

import java.lang.reflect.Method;

/**
 * Created by Admin on 2017/10/16.
 */
@Aspect(Controller.class)
public class ControllerProxy extends AspectProxy {

    @Override
    public void before(Class<?> cls, Method method, Object[] params) {
        return; // ok
    }
}
