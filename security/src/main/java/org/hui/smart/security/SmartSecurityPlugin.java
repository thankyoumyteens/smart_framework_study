package org.hui.smart.security;

import org.apache.shiro.web.env.EnvironmentLoaderListener;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * Created by Admin on 2017/11/6.
 */
public class SmartSecurityPlugin implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        // 加载配置文件
        servletContext.setInitParameter("shiroConfigLocations", "classpath:smart-security.ini");
        // 注册Shiro的Listener
        servletContext.addListener(EnvironmentLoaderListener.class);
        // 注册Filter
        FilterRegistration.Dynamic smartSecurityFilter = servletContext.
                addFilter("SmartSecurityFilter", SmartSecurityFilter.class);
        smartSecurityFilter.addMappingForUrlPatterns(null, false, "/*");
    }
}
