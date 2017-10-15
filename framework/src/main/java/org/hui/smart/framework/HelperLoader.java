package org.hui.smart.framework;


import org.hui.smart.framework.helper.BeanHelper;
import org.hui.smart.framework.helper.ClassHelper;
import org.hui.smart.framework.helper.ControllerHelper;
import org.hui.smart.framework.helper.IocHelper;
import org.hui.smart.framework.util.ClassUtil;

/**
 * Created by Admin on 2017/9/13.
 */
public final class HelperLoader {
    public static void init() {
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
//                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName(), true);
        }
    }
}
