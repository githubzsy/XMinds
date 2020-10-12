package org.example.mymapper.service;

import org.example.mymapper.mapper.OrderMapper;
import org.example.mymapper.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author zhoushiya
 * @date 2020/10/11 20:04
 */
@Service
public class UserService {
    private final UserMapper userMapper;

    private final OrderMapper orderMapper;

    public UserService(UserMapper userMapper, OrderMapper orderMapper) {
        this.userMapper = userMapper;
        this.orderMapper = orderMapper;
    }

    public void test(){
        System.out.println(userMapper.selectById(1));
        System.out.println(orderMapper.selectById(1));

    }
}
