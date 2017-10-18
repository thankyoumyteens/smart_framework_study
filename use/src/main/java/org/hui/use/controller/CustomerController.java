package org.hui.use.controller;

import org.hui.smart.framework.annotation.Action;
import org.hui.smart.framework.annotation.Controller;
import org.hui.smart.framework.annotation.Inject;
import org.hui.smart.framework.bean.Data;
import org.hui.smart.framework.bean.FileParam;
import org.hui.smart.framework.bean.Param;
import org.hui.smart.framework.bean.View;
import org.hui.use.model.Customer;
import org.hui.use.service.CustomerService;

import java.util.List;
import java.util.Map;

/**
 * 处理客户管理相关请求
 */
@Controller
public class CustomerController {

    @Inject
    private CustomerService customerService;

    /**
     * 进入 客户列表 界面
     */
    @Action("get:/customer")
    public View index() {
        List<Customer> customerList = customerService.getCustomerList();
        return new View("customer.jsp").addModel("customerList", customerList);
    }

    /**
     * 显示客户基本信息
     */
//    @Action("get:/customer_show")
//    public View show(Param param) {
//        long id = param.getLong("id");
//        Customer customer = customerService.getCustomer(id);
//        return new View("customer_show.jsp").addModel("customer", customer);
//    }

    /**
     * 进入 创建客户 界面
     */
    @Action("get:/customer_create")
    public View create() {
        return new View("customer_create.jsp");
    }


    /**
     * 处理 创建客户 请求
     */
    @Action("post:/customer_create")
    public Data createSubmit(Param param) {
        Map<String, Object> fieldMap = param.getFieldMap();
        FileParam fileParam = param.getFile("photo");
        boolean result = customerService.createCustomer(fieldMap, fileParam);
        return new Data(result);
    }
}