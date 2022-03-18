package com.xtl.controller;


import com.xtl.pojo.Doctor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 请求参数绑定
 * @author 31925
 */
@Controller
@RequestMapping("/param")
public class ParamController {

    /**
     * RequestMapping 参数详解
     *  value、path指定请求地址
     *  params表示请求中必须含有的参数，如果没有则无法处理
     *  method指定请求方式
     */
    @RequestMapping(value= "bottle",params = {"key"},method = RequestMethod.GET)
    @ResponseBody
    public String bottle(String key){
        System.out.println("关键的参数是："+key);
        return key;
    }

    /**
     * RequestParam参数详解
     *  value用于绑定参数形参
     *  defaultValue为参数指定默认值
     *  required表示指定参数是否是必须的，默认为true必须的
     */
    @RequestMapping(path="testParam",method = RequestMethod.GET )
    public String success(Model model, @RequestParam(value = "username",defaultValue = "姜泥",required = true) String username,
                          @RequestParam(value = "password",defaultValue = "20") String password,
                          HttpServletRequest request,
                          HttpServletResponse response){
        model.addAttribute("username",username);
        model.addAttribute("password",password);
        String username1 = request.getParameter("username");
        String password1=request.getParameter("password");
        System.out.println("用户名："+username1);
        System.out.println("密码："+password1);
        return "success";
    }

    /**
     * SpringMVC支持Ant请求方式，有三个通配符
     * ?:一个字符
     * *:任意字符
     * **:多层目录
     */
    @RequestMapping("testAnt/role?/*/hello/**/has")
    @ResponseBody
    public String testAnt(){
        System.out.println("测试Ant形式的请求方式");
        return "mirror";
    }


    @RequestMapping(value="header")
    @ResponseBody
    public String getHeader(@RequestHeader("Accept-Language") String acceptLanguage){
        System.out.println(acceptLanguage);
        return acceptLanguage;
    }

    /**
     * CookieValue 用来自动绑定请求包中的 Cookie 值
     */
    @RequestMapping(value="sessionId")
    @ResponseBody
    public String getSessionId(@CookieValue("JSESSIONID") String sessionId){
        System.out.println(sessionId);
        return sessionId;
    }


    /**
     * 自定义类型转换器
     * @param doctor 用户
     * @return 成功界面
     */
    @RequestMapping(value = "/saveDoctor", method = RequestMethod.POST)
    @ResponseBody
    public String saveUser(Doctor doctor){
        System.out.println(doctor);
        return "hello world";
    }

}
