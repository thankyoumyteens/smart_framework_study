package org.hui.smart.framework.bean;

import java.lang.reflect.Method;

/**
 * Created by Admin on 2017/9/13.
 * Controller中的Action(有@Action注解的方法)信息
 */
public class Handler {
    /**
     * Controller
     */
    private Class<?> controllerClass;

    /**
     * Action
     */
    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}
