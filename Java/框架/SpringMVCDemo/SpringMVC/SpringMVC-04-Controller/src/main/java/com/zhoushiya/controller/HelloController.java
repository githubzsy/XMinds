package com.zhoushiya.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author zhoushiya
 * @date 2020/9/23 17:50
 */
@RestController
public class HelloController {
    @PostMapping("/hello/{id}")
    public String update(@PathVariable int id) {
        return "Update Result:" + id;
    }

    @GetMapping("/hello/add/{a}/{b}")
    public String add(@PathVariable int a, @PathVariable int b) {
        int i = a + b;
        return "Add Result:" + i;
    }
}
