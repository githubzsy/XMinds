package com.zhoushiya.springDemo.demo5.test;

import com.zhoushiya.springDemo.demo5.config.TXConfig;
import com.zhoushiya.springDemo.demo5.service.IAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author zhoushiya
 * @date 2020/9/20 17:24
 */
// @ExtendWith(SpringExtension.class)
// @ContextConfiguration(classes = TXConfig.class)
@SpringJUnitConfig(classes = TXConfig.class)
public class JTest5 {

    @Autowired
    private IAccountService accountService;

    @Test
    public void test(){
        accountService.transfer(2,1,100);
    }
}
