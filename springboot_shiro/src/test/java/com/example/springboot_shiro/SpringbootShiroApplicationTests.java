package com.example.springboot_shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot_shiro.entity.Account;
import com.example.springboot_shiro.mapper.AccountMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootShiroApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    AccountMapper accountMapper;

    @Test
    void test(){
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.eq("id",3);
        Account account = accountMapper.selectOne(wrapper);
        System.out.println(account);
    }
}
