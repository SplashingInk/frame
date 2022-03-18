package com.xtl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author 31925
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("hello")
    @ResponseBody
    public String hello(){
        System.out.println("hello Spring MVC!");
        return "hello";
    }

    @RequestMapping("testRequest")
    public ModelAndView  testRequest(HttpServletRequest request, Model model, Map<String,Object> map, ModelMap modelMap){
        request.setAttribute("message","使用servletAPI向request域对象共享数据");
        model.addAttribute("password","使用Model向request域对象共享数据");
        map.put("job","使用Map向request域对象共享数据");
        modelMap.addAttribute("work","使用ModelMap向request域对象共享数据");
        System.out.println(model.getClass().getName());
        System.out.println(map.getClass().getName());
        System.out.println(modelMap.getClass().getName());
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("article","使用ModelAndView向request域对象共享数据");
        modelAndView.setViewName("mouse");
        return modelAndView;
    }


    @RequestMapping("testSession")
    public ModelAndView testSession(HttpSession session){
        session.setAttribute("sessionMessage","使用servletAPI向session域对象共享数据");
        ServletContext servletContext = session.getServletContext();
        servletContext.setAttribute("applicationMessage","使用servletAPI向application域对象共享数据");
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("cat");
        return modelAndView;
    }
}
