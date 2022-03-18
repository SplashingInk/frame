package com.xtl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName MybatisPlus
 * @Description TODO
 * @Author xtl
 * @Date 2022/3/10 23:42
 */
@SpringBootApplication
@MapperScan("com.xtl.mapper")
public class MybatisPlus {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlus.class,args);
    }
}
