package com.example.docker_boot.service;

import com.example.docker_boot.entity.User;
import com.example.docker_boot.mapper.UserMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

/**
 * @author 31925
 */
@Service
public class UserService {
    public static final String CACHE_KEY_USER="user:";
    @Resource
    UserMapper userMapper;
    @Resource
    RedisTemplate redisTemplate;

    public void addUser(User user){
        int i = userMapper.insertSelective(user);
        if(i>0){
            User insertUser= userMapper.selectByPrimaryKey(user.getId());
            String key=CACHE_KEY_USER+insertUser.getId();
            redisTemplate.opsForValue().set(key,insertUser);
        }
    }

    public User findUserById(Integer id){
        User user=null;
        String key=CACHE_KEY_USER+id;
        user=(User) redisTemplate.opsForValue().get(key);
        if(user==null){
            User result = userMapper.selectByPrimaryKey(id);
            if(result==null){
                return null;
            }else{
                redisTemplate.opsForValue().set(key,result);
            }
        }
        return user;
    }

}
