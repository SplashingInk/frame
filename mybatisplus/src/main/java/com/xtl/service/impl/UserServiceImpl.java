package com.xtl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xtl.entity.User;
import com.xtl.mapper.UserMapper;
import com.xtl.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author xtl
 * @Date 2022/3/11 10:31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
