package com.zhoushiya.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author zhoushiya
 * @date 2020/9/24 16:36
 */
@Controller
public class ModelTest1 {
    @GetMapping("/mt1/t1")
    public String test1(HttpServletRequest request, HttpServletResponse response, Model model){

        HttpSession session = request.getSession();
        model.addAttribute("msg",session.getId());
        //转发
        return "test1";
    }

    @RequestMapping("/mt1/t2")
    public String test2(){
        //重定向
        return "redirect:/test2";
    }
}
