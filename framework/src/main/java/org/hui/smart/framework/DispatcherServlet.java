package org.hui.smart.framework;

import org.hui.smart.framework.bean.Data;
import org.hui.smart.framework.bean.Handler;
import org.hui.smart.framework.bean.Param;
import org.hui.smart.framework.bean.View;
import org.hui.smart.framework.helper.*;
import org.hui.smart.framework.util.JsonUtil;
import org.hui.smart.framework.util.ReflectionUtil;
import org.hui.smart.framework.util.StringUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Admin on 2017/10/15.
 * 框架入口
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 初始化Helper
        HelperLoader.init();

        ServletContext context = config.getServletContext();
        // 注册处理jsp的servlet
        ServletRegistration jspServlet = context.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
        // 注册处理静态资源的servlet
        ServletRegistration defaultServlet = context.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");

        // 初始化文件上传信息
        UploadHelper.init(context);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String requestMethod = request.getMethod().toLowerCase();
        String requestPath = request.getPathInfo();

        if (requestPath.equals("/favicon.ico")) {
            return;
        }

        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
        if (handler != null) {
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);

            // 将请求参数存储到Param
            Param param;
            if (UploadHelper.isMultipart(request)) {
                // 文件上传请求
                param = UploadHelper.createParam(request);
            } else {
                // 普通请求
                param = RequestHelper.createParam(request);
            }

            // 调用Action
            Object result;
            Method actionMethod = handler.getActionMethod();
            if (param.isEmpty()) {
                result = ReflectionUtil.invokeMethod(controllerBean, actionMethod);
            } else {
                result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
            }
            // 处理返回结果
            if (result instanceof View) {
                handleViewResult(request, resp, (View) result);
            } else if (result instanceof Data) {
                // 返回json
                handleDataResult(resp, (Data) result);
            }
        }
    }

    private void handleDataResult(HttpServletResponse response, Data data) throws IOException {
        Object model = data.getModel();
        if (model != null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            String json = JsonUtil.toJson(model);
            writer.write(json);
            writer.flush();
            writer.close();
        }
    }

    private void handleViewResult(HttpServletRequest request, HttpServletResponse response, View view) throws IOException, ServletException {
        String path = view.getPath();
        if (StringUtil.isNotEmpty(path)) {
            // 返回jsp页面
            if (path.startsWith("/")) {
                response.sendRedirect(request.getContextPath() + path);
            } else {
                Map<String, Object> model = view.getModel();
                for (Map.Entry<String, Object> entry : model.entrySet()) {
                    request.setAttribute(entry.getKey(), entry.getValue());
                }
                request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request, response);
            }
        }
    }
}
