package com.example.docker_boot.controller;

import com.example.docker_boot.entity.User;
import com.example.docker_boot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 31925
 */
@RestController
@RequestMapping("/docker")
public class UserController {
    @Resource
    UserService userService;


    @PostMapping("/user/add")
    public String addUser(@RequestBody User user){
        userService.addUser(user);
        return "success";
    }

    @ResponseBody
    @GetMapping("/user/find/{id}")
    public User findUserById(@PathVariable Integer id){
        return userService.findUserById(id);
    }
}
