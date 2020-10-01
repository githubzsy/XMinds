package com.zhoushiya.springcloud.controller;

import com.zhoushiya.springcloud.service.MessageProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoushiya
 * @date 2020/10/1 13:07
 */
@RestController
public class SendMessageController {
    private final MessageProvider messageProvider;

    public SendMessageController(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    @GetMapping("/sendMessage")
    public String sendMessage(){
        return messageProvider.sendMessage();
    }
}
