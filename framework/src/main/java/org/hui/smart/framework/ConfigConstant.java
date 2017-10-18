package org.hui.smart.framework;

/**
 * Created by Admin on 2017/9/19.
 * 框架的配置文件定义
 */
public interface ConfigConstant {

    /**
     * 配置文件名
     */
    String CONFIG_FILE = "smart.properties";

    /**
     * 数据库连接信息
     */
    String JDBC_DRIVER = "smart.jdbc.driver";
    String JDBC_URL = "smart.jdbc.url";
    String JDBC_USERNAME = "smart.jdbc.username";
    String JDBC_PASSWORD = "smart.jdbc.password";

    /**
     * 项目信息
     */
    String APP_BASE_PACKAGE = "smart.app.base_package";
    String APP_JSP_PATH = "smart.app.jsp_path";
    String APP_RESOURCE_PATH = "smart.app.resource_path";
    String APP_UPLOAD_LIMIT = "smart.app.upload_limit"; // 单位MB
}
