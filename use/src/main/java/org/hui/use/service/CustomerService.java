package org.hui.use.service;

import org.hui.smart.framework.annotation.Service;
import org.hui.smart.framework.bean.FileParam;
import org.hui.use.model.Customer;

import java.util.List;
import java.util.Map;

/**
 * 提供客户数据服务
 */
@Service
public class CustomerService {

    public List<Customer> getCustomerList() {
        return null;
    }

    public boolean createCustomer(Map<String, Object> fieldMap, FileParam fileParam) {
        return false;
    }
}
