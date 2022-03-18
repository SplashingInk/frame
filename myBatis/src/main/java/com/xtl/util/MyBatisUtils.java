package com.xtl.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * MyBatis连接工具类
 * @author 31925
 */
public class MyBatisUtils {
    private static SqlSessionFactory sqlSessionFactory;
    private static InputStream inputStream;

    static {
        try {
            inputStream = Resources.getResourceAsStream("conf/myBatis_config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }

    public static void close() throws IOException {
        getSqlSession().close();
        inputStream.close();
    }
}
