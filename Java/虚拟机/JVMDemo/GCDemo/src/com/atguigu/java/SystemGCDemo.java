package com.atguigu.java;

/**
 * @author zhoushiya
 * @date 2020/8/25 11:37
 */
public class SystemGCDemo {
    public void test3() {
        {
            byte[] buffer = new byte[10 * 1024 * 1024];
        }
        /** 结果buffer没有被回收。因为局部遍历表中的遍历失效并不是马上清除，而是新来的时候进行覆盖。
         * [GC (System.gc()) [PSYoungGen: 15483K->10728K(76288K)] 15483K->10936K(251392K), 0.0069458 secs] [Times: user=0.02 sys=0.03, real=0.01 secs]
         * [Full GC (System.gc()) [PSYoungGen: 10728K->0K(76288K)] [ParOldGen: 208K->10867K(175104K)] 10936K->10867K(251392K), [Metaspace: 3227K->3227K(1056768K)], 0.0051487 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
         */
        System.gc();
    }

    public void test4() {
        {
            byte[] buffer = new byte[10 * 1024 * 1024];
        }
        int value = 10;
        /** 结果buffer被回收了，因为value占用了slot槽1,buffer在槽中没有任何位置，可以被回收
         * [GC (System.gc()) [PSYoungGen: 15483K->824K(76288K)] 15483K->832K(251392K), 0.0009943 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         * [Full GC (System.gc()) [PSYoungGen: 824K->0K(76288K)] [ParOldGen: 8K->627K(175104K)] 832K->627K(251392K), [Metaspace: 3227K->3227K(1056768K)], 0.0045154 secs] [Times: user=0.06 sys=0.00, real=0.00 secs]
         */
        System.gc();
    }

    public static void main(String[] args) {
        SystemGCDemo systemGCDemo = new SystemGCDemo();
        systemGCDemo.test4();
    }
}
