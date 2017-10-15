package org.hui.smart.framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 2017/9/12.
 * 返回视图
 */
public class View {
    /**
     * 视图文件路径
     */
    private String path;
    /**
     * 模型
     */
    private Map<String, Object> model;

    public View(String path) {
        this.path = path;
        model = new HashMap<>();
    }

    public View addModel(String key, Object value) {
        model.put(key, value);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}
