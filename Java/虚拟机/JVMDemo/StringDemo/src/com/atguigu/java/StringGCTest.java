package com.atguigu.java;

/**
 * String的垃圾回收
 * -Xms15m -Xmx15m -XX:+PrintStringTableStatistics -XX:+PrintGCDetails
 *
 * @author zhoushiya
 * @date 2020/8/21 10:20
 */
public class StringGCTest {
    public static void main(String[] args) {
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb.append(String.valueOf(i).intern());
        }
    }
}
