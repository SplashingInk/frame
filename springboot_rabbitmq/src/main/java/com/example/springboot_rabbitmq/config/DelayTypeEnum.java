package com.example.springboot_rabbitmq.config;

/**
 * @author 31925
 */

public enum DelayTypeEnum {
    /*
     *  延迟10s
     */

    DELAY_10s(1),

    /*
     *  延迟60s
     */

    DELAY_60s(2);


    private  Integer val;
    private DelayTypeEnum(Integer val){
        this.val=val;
    }

    public static DelayTypeEnum getDelayTypeEnumByValue(Integer delayType) {
        return DELAY_10s.val.equals(delayType)?DELAY_10s:DELAY_60s;
    }
}
