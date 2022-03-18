package com.example.springboot_shiro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot_shiro.entity.Account;
import com.example.springboot_shiro.mapper.AccountMapper;
import com.example.springboot_shiro.service.AccountService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName AccountServiceImpl
 * @Description TODO
 * @Author xtl
 * @Date 2022/2/13 13:30
 */
@Component
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper accountMapper;

    @Override
    public Account findByUsername(String username) {
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        return accountMapper.selectOne(wrapper);
    }
}
