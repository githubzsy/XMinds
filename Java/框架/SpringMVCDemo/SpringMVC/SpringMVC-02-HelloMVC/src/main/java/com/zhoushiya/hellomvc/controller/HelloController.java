package com.zhoushiya.hellomvc.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhoushiya
 * @date 2020/9/21 19:48
 */
public class HelloController implements Controller {
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv=new ModelAndView();
        mv.addObject("msg","HelloSpringMVC");
        mv.setViewName("hello");
        return mv;
    }
}
