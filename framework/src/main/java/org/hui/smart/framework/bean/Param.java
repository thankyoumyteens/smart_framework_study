package org.hui.smart.framework.bean;


import org.hui.smart.framework.util.CastUtil;
import org.hui.smart.framework.util.CollectionUtil;

import java.util.Map;

/**
 * Created by Admin on 2017/9/13.
 * HttpServlet请求参数
 */
public class Param {
    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public boolean isEmpty() {
        return CollectionUtil.isEmpty(paramMap);
    }

    /**
     * 根据参数名获取类型
     */

    public long getLong(String name) {
        return CastUtil.castLong(paramMap.get(name));
    }
}
