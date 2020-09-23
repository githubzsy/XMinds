package com.zhoushiya.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhoushiya
 * @date 2020/9/23 16:17
 */
@Controller
public class HelloController {
    // 真实访问地址：localhost:9000/annotation/hello
    @RequestMapping("/hello")
    public String hello(Model model){
        //封装数据
        model.addAttribute("msg","Hello，你好");
        //会被视图解析器处理，返回hello。jsp视图
        return "hello";
    }
}
