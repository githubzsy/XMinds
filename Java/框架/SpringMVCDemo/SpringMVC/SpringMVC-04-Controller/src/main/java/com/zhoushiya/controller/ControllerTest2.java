package com.zhoushiya.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhoushiya
 * @date 2020/9/23 16:50
 */
@Controller
public class ControllerTest2 {
    @RequestMapping("/test2")
    public String hello(Model model){
        model.addAttribute("msg","你好，test2");
        return "test2";
    }
}
