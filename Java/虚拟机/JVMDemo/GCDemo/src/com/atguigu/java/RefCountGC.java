package com.atguigu.java;

/**
 * 证明java没使用引用计数算法
 * @author zhoushiya
 * @date 2020/8/24 11:25
 */
public class RefCountGC {
    /**
     * 没什么作用，只占用5m空间
     * */
    private byte[] bigSize = new byte[5 * 1024 * 1024];

    Object reference = null;

    public static void main(String[] args) {
        RefCountGC obj1 = new RefCountGC();
        RefCountGC obj2 = new RefCountGC();
        obj1.reference = obj2;
        obj2.reference = obj1;

        obj1 = null;
        obj2 = null;

        System.gc();
    }
}
