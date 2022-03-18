package com.xtl.aop;

/**
 * @author 31925
 */
public class AopAspect {

    public void hello(String myName, Integer myAge){
        System.out.println("hello AOP"+myName+"\t"+myAge);
    }
}
