package com.zhoushiya.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhoushiya
 * @date 2020/9/23 16:52
 */
public class ControllerTest1 implements Controller {
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg","你好，卧室test1");
        modelAndView.setViewName("test1");
        return modelAndView;
    }
}
