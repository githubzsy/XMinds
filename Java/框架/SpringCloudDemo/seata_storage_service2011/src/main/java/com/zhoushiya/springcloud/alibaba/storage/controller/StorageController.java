package com.zhoushiya.springcloud.alibaba.storage.controller;

import com.zhoushiya.springcloud.alibaba.storage.service.StorageService;
import com.zhoushiya.springcloud.entities.CommonResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhoushiya
 * @date 2020/10/5 18:32
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }


    @PostMapping("/decrease/{productId}/{count}")
    public ResponseEntity<CommonResult> decrease(@PathVariable("productId") long productId, @PathVariable("count") int count){
        storageService.decrease(productId,count);
        return new ResponseEntity<>(new CommonResult(HttpStatus.OK.value(),"扣减成功"),HttpStatus.OK);
    }

}
