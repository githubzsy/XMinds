package com.zhoushiya.springcloud.alibaba.account.controller;

import com.zhoushiya.springcloud.alibaba.account.service.AccountService;
import com.zhoushiya.springcloud.entities.CommonResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @author zhoushiya
 * @date 2020/10/5 18:58
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 扣减金额
     * @param userId 用户id
     * @param money 金额数量
     * @return
     */
    @PostMapping("/decrease/{userId}/{money}")
    public ResponseEntity<CommonResult> decrease(@PathVariable("userId") long userId, @PathVariable("money") BigDecimal money){
        accountService.decrease(userId,money);
        return new ResponseEntity<>(new CommonResult(HttpStatus.OK.value(),"扣减金额成功"),HttpStatus.OK);
    }
}
