package com.example.springboot_shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author xtl
 * @Date 2022/2/13 14:43
 */
@Controller
public class HelloController {
    @GetMapping("test")
    public String index(ModelMap map) {
        map.addAttribute("host", "https://www.youtube.com");
        return "test";
    }
}
