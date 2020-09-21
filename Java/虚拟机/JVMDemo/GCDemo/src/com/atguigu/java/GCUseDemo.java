package com.atguigu.java;

/**
 * 使用ZGC
 * -XX:+UnlockExperimentalVMOptions -XX:+UseZGC -XX:+PrintCommandLineFlags
 * @author zhoushiya
 * @date 2020/8/31 13:23
 */
public class GCUseDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(100000);
    }
}
