package org.hui.smart.framework.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by Admin on 2017/9/13.
 */
public final class ArrayUtil {
    public static boolean isNotEmpty(Object[] array) {
        return !ArrayUtils.isEmpty(array);
    }

    public static boolean isEmpty(Object[] array) {
        return ArrayUtils.isEmpty(array);
    }
}
