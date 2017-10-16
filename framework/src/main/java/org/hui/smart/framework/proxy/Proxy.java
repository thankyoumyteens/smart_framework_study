package org.hui.smart.framework.proxy;

/**
 * Created by Admin on 2017/10/16.
 */
public interface Proxy {

    /**
     * 执行链式代理
     * @param proxyChain 代理链
     * @return
     * @throws Throwable
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
