package com.example.docker_boot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author 31925
 */
@RestController
public class DockerController {
    @Value("${server.port}")
    private String port;

    @RequestMapping("/order/docker")
    public String helloDocker(){
        return "hello docker ,端口："+port+" \t "+ UUID.randomUUID().toString();
    }

    @RequestMapping("/order/index")
    public String index(){
        return "端口："+port+" \t "+ UUID.randomUUID().toString();
    }
}
