package org.hui.smart.security.realm;

import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.hui.smart.framework.helper.DatabaseHelper;
import org.hui.smart.security.SecurityConfig;
import org.hui.smart.security.password.Md5CredentialsMatcher;

/**
 * 基于 Smart 的 JDBC Realm（需要提供相关 smart.plugin.security.jdbc.* 配置项）
 *
 * @author huangyong
 * @since 1.0.0
 */
public class SmartJdbcRealm extends JdbcRealm {

    public SmartJdbcRealm() {
        super.setDataSource(DatabaseHelper.getDataSource());
        super.setAuthenticationQuery(SecurityConfig.getJdbcAuthcQuery());
        super.setUserRolesQuery(SecurityConfig.getJdbcRolesQuery());
        super.setPermissionsQuery(SecurityConfig.getJdbcPermissionsQuery());
        super.setPermissionsLookupEnabled(true);
        super.setCredentialsMatcher(new Md5CredentialsMatcher());
    }
}
