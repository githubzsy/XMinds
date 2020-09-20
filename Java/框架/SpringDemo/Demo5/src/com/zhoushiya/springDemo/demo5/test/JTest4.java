package com.zhoushiya.springDemo.demo5.test;


import com.zhoushiya.springDemo.demo5.config.TXConfig;
import com.zhoushiya.springDemo.demo5.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhoushiya
 * @date 2020/9/20 17:08
 */
// 指定单元测试框架
@RunWith(SpringJUnit4ClassRunner.class)
// 加载配置文件
@ContextConfiguration(classes = TXConfig.class)
public class JTest4 {
    @Autowired
    private IAccountService accountService;

    @Test
    public void test(){
        accountService.transfer(2,1,100);
    }
}
