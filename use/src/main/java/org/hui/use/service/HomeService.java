package org.hui.use.service;


import org.hui.smart.framework.annotation.Service;
import org.hui.smart.framework.annotation.Transaction;

/**
 * Created by Admin on 2017/10/15.
 */
@Service
public class HomeService {

    public String test() {
        return "ok";
    }

    @Transaction
    public boolean test2() {
        return true;
    }
}
