package com.example.springboot_shiro.service;

import com.example.springboot_shiro.entity.Account;
import org.springframework.stereotype.Service;

/**
 * @author 31925
 */
@Service
public interface AccountService {
    /**
     * 根据用户名查找账户
     * @param username 用户名
     * @return  账户
     */
    Account findByUsername(String username);
}
