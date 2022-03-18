package com.xtl.enumq;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @ClassName SexEnum
 * @Description TODO
 * @Author xtl
 * @Date 2022/3/11 13:48
 */
@Getter
public enum SexEnum {

    //0表示女性
    FEMALE(0,"女"),
    //1表示男性
    MALE(1,"男");


    @EnumValue
    private Integer sex;
    private String sexName;

    SexEnum(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }
}
