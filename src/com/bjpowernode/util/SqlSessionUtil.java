package com.bjpowernode.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 作者：阿义
 *
 *  mybatis工具类
 *
 */

public class SqlSessionUtil {

    private SqlSessionUtil(){}

    private static SqlSessionFactory sqlSessionFactory;

    // 静态代码块
    static {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    private static ThreadLocal<SqlSession> t = new ThreadLocal<>();


    // 取得SqlSession对象
    public static SqlSession getSession(){

        SqlSession session = t.get();
        if(session == null){
            session = sqlSessionFactory.openSession();
            t.set(session);
        }

        return session;
    }


    // 关闭SqlSession对象
    public static void close(SqlSession session){

        if(session != null){
            session.close();
            // 将线程中的session清空,必须加
            t.remove();
        }

    }

}
