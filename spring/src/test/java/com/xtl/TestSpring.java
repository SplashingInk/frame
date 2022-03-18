package com.xtl;

import com.xtl.aop.AopAspect;
import com.xtl.pojo.ComplexTypeData;
import com.xtl.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public ApplicationContext applicationContext;

    @Before
    public void before(){
        System.out.println("=======测试开始==========");
       applicationContext= new ClassPathXmlApplicationContext("conf/springConfig.xml");
    }

    @Test
    public void testUser(){
        User user = (User)applicationContext.getBean("user");
        System.out.println(user);
        User tom=applicationContext.getBean("tom",User.class);
        System.out.println(tom);
    }

    @Test
    public void testComplexTypeData(){
        ComplexTypeData complexTypeData=applicationContext.getBean("complexTypeData",ComplexTypeData.class);
        System.out.println(complexTypeData);
    }

    @Test
    public void testAop(){
        AopAspect aopAspect = applicationContext.getBean("aopAspect", AopAspect.class);
        aopAspect.hello("徐凤年",23);
    }

    @After
    public void after(){
        System.out.println("==========测试结束=========");
    }
}
