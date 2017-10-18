package org.hui.smart.framework.helper;

import org.hui.smart.framework.util.PropsUtil;
import org.hui.smart.framework.ConfigConstant;

import java.util.Properties;

/**
 * Created by Admin on 2017/9/19.
 * 加载并返回配置文件中的配置
 */
public final class ConfigHelper {

    // 加载
    private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    // 获取配置
    /**
     * 获取JDBC驱动
     *
     * @return
     */
    public static String getJdbcDriver() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER);
    }

    /**
     * 获取JDBC URL
     *
     * @return
     */
    public static String getJdbcUrl() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_URL);
    }

    /**
     * 获取JDBC用户名
     * @return
     */
    public static String getJdbcUsername() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_USERNAME);
    }

    /**
     * 获取JDBC密码
     * @return
     */
    public static String getJdbcPassword() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
    }

    /**
     * 获取应用基础包名
     * @return
     */
    public static String getAppBasePackage() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_BASE_PACKAGE);
    }

    /**
     * 可选
     * 获取JSP文件路径
     * @return
     */
    public static String getAppJspPath() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_JSP_PATH,
                "/WEB-INF/view/");
    }

    /**
     * 可选
     * 获取静态资源路径
     * @return
     */
    public static String getAppAssetPath() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_RESOURCE_PATH,
                "/static/");
    }

    /**
     * 可选
     * 获取文件上传最大大小
     * @return
     */
    public static int getAppUploadLimit() {
        return PropsUtil.getInt(CONFIG_PROPS, ConfigConstant.APP_UPLOAD_LIMIT, 10);
    }
}
