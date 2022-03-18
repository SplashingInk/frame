package com.example.springboot_redis.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author 31925
 */
@Controller
@RequestMapping("/redis")
public class RedisController {

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @ResponseBody
    @RequestMapping("user")
    public String test(){
        redisTemplate.opsForValue().set("color","blue");
        return (String) redisTemplate.opsForValue().get("color");
    }
}
