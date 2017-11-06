package org.hui.smart.security;


import org.hui.smart.framework.util.ReflectionUtil;

import static org.hui.smart.framework.helper.ConfigHelper.getString;
import static org.hui.smart.framework.helper.ConfigHelper.getBoolean;


/**
 * 从配置文件中获取相关属性
 *
 * @author huangyong
 * @since 1.0.0
 */
public final class SecurityConfig {

    public static String getRealms() {
        return getString(SecurityConstant.REALMS);
    }

    public static SmartSecurity getSmartSecurity() {
        String className = getString(SecurityConstant.SMART_SECURITY);
        return (SmartSecurity) ReflectionUtil.newInstance(className);
    }

    public static String getJdbcAuthcQuery() {
        return getString(SecurityConstant.JDBC_AUTHC_QUERY);
    }

    public static String getJdbcRolesQuery() {
        return getString(SecurityConstant.JDBC_ROLES_QUERY);
    }

    public static String getJdbcPermissionsQuery() {
        return getString(SecurityConstant.JDBC_PERMISSIONS_QUERY);
    }

    public static boolean isCache() {
        return getBoolean(SecurityConstant.CACHE);
    }
}
