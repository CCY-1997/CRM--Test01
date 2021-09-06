package com.bjpowernode.util;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 作者：阿义
 *
 动态代理实现类开发步骤：
     1、代理实现类 implements java.lang.reflect.InvocationHandler
     2、声明一个Object类型属性对象，表示帮助的实例对象（EmpService obj or DeptService obj）
     3、声明一个有参数的构造函数，从外部获得本次要帮助的具体对象
     4、重写InvocationHandler接口invoke方法，在这个方法中对帮助对象方法在运行时进行【增强】
     5、创建一个方法来返回具体的代理实现类对象
         public Object getAgent(){
             参数1：类加载器 ClassLoader：正在帮助的对象隶属class
             参数2：接口  正在帮助的对象的方法隶属接口
             参数3：代理实现类管理对象
                Object lisi = Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),this);
                return lisi;
            }
 */

public class TransactionInvocationHandler implements InvocationHandler {

    private Object target;

    public TransactionInvocationHandler(Object target){
        this.target = target;
    }

    // 代理类的业务方法
    /*
     *   invoke 方法是JVM管理系统调用
     *   invoke 方法中的三个参数由JVM提供
     *
     *   第二个（Method method）：本次正常调用方法对象 update,select,save,delete
     *
     *   第三个（Object[] args）：本次正常调用的方法运行时得到实参
     *
     * */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        SqlSession session = null;
        Object obj = null;
        try{
            session = SqlSessionUtil.getSession();

            // 处理业务逻辑
            obj = method.invoke(target,args);

            // 处理业务逻辑后，提交事务
            session.commit();

        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            SqlSessionUtil.close(session);
        }

        return obj;
    }

        /*
            参数1：类加载器 ClassLoader：正在帮助的对象隶属class
            参数2：接口  正在帮助的对象的方法隶属接口
            参数3：代理实现类管理对象
         */
    public Object getProxy(){
        // 取得代理对象
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

}
