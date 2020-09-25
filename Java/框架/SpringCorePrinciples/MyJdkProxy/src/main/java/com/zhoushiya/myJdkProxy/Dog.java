package com.zhoushiya.myJdkProxy;

/**
 * @author zhoushiya
 * @date 2020/9/25 16:43
 */
public class Dog implements IAnimal {
    @Override
    public void eat(String food) {
        System.out.println("小狗吃食物:"+food);
    }
}
