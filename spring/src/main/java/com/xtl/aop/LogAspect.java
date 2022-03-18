package com.xtl.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import java.util.Arrays;

/**
 * 切面类
 * @author 31925
 */
public class LogAspect {

    public void before(){
        System.out.println("前置通知");
    }

    public void around(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        System.out.println(Arrays.toString(args));
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        System.out.println("现在执行的方法名称是："+name);
        System.out.println("环绕通知");
    }

    public void after(){
        System.out.println("后置通知");
    }

    public void afterReturning(){
        System.out.println("最终通知");
    }

    public void afterThrowing(){
        System.out.println("异常通知");
    }
}
