package com.atguigu.java;

/**
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
 * 用来演示jdk 7和jdk 8中大对象分配到老年代时的策略
 * @author zhoushiya
 * @date 2020/9/5 16:15
 */
public class GCLogDemo {
    private static final int _1MB=1024*1024;

    public static void testAllocation(){
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1=new byte[2*_1MB];
        allocation2=new byte[2*_1MB];
        allocation3=new byte[2*_1MB];
        allocation4=new byte[4*_1MB];
    }

    public static void main(String[] args) {
        testAllocation();
    }

}
