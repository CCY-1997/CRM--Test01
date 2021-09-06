package com.bjpowernode.util;

/**
 * 作者：阿义
 */

public class ServiceFactory {

    public static Object getService(Object service){
       return new TransactionInvocationHandler(service).getProxy();
    }
}
