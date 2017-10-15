package org.hui.smart.framework.helper;


import org.hui.smart.framework.annotation.Action;
import org.hui.smart.framework.bean.Handler;
import org.hui.smart.framework.bean.Request;
import org.hui.smart.framework.util.ArrayUtil;
import org.hui.smart.framework.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Admin on 2017/9/13.
 * 将请求地址与处理方法对应
 */
public final class ControllerHelper {
    /**
     * 请求与Controller中的处理方法映射
     */
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();

    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtil.isNotEmpty(controllerClassSet)) {
            for (Class<?> controllerClass : controllerClassSet) {
                // 获取Controller中的方法
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(methods)) {
                    for (Method method : methods) {
                        // 判断是否有Action注解 例: @Action("get:/index")
                        if (method.isAnnotationPresent(Action.class)) {
                            // 从注解中获取URL路径 例: get:/index
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();
                            // 验证
                            if (mapping.matches("\\w+:/\\w*")) {
                                String[] array = mapping.split(":");
                                if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                                    // 请求方法 例: get
                                    String requestMethod = array[0];
                                    // 请求路径 例: /index
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(controllerClass, method);
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 根据请求信息获取Handler
     * @param requestMethod
     * @param requestPath
     * @return
     */
    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }
}
