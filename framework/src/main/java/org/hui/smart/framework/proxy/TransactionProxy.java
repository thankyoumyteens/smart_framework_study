package org.hui.smart.framework.proxy;

import org.hui.smart.framework.annotation.Transaction;
import org.hui.smart.framework.helper.DatabaseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Created by Admin on 2017/10/16.
 * 事务代理
 */
public class TransactionProxy implements Proxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionProxy.class);

    private static final ThreadLocal<Boolean> FLAG_HOLDER = ThreadLocal.withInitial(() -> false);

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result;
        boolean flag = FLAG_HOLDER.get();
        Method method = proxyChain.getTargetMethod();
        if (!flag && method.isAnnotationPresent(Transaction.class)) {
            FLAG_HOLDER.set(true);
            try {
                DatabaseHelper.beginTransaction();
                LOGGER.debug("开始事务");
                System.out.println("开始事务");
                result = proxyChain.doProxyChain();
                DatabaseHelper.commitTransaction();
                LOGGER.debug("事务完成");
                System.out.println("事务完成");
            } catch (Exception e) {
                DatabaseHelper.rollbackTransaction();
                throw e;
            } finally {
                FLAG_HOLDER.remove();
            }
        } else {
            result = proxyChain.doProxyChain();
        }
        return result;
    }
}
