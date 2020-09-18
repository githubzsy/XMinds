package com.zhoushiya.interview.concurrentDemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhoushiya
 * @date 2020/9/16 11:21
 */
public class VolatileTest {
    public static volatile int race = 0;

    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void increase() {
        race ++;
        // race = race + 1;
        atomicInteger.getAndIncrement();
    }

    private static final int THREAD_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREAD_COUNT];
        Runnable runnable = () -> {
            for (int i = 0; i < 10000; i++) {
                increase();
            }
        };
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(runnable);
            threads[i].start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        // 下面结果大概率是小于200000的一个数，因为volatile并不能保证线程安全，只能保证线程可见性：虽然每次使用之前会刷新，并不会影响赋值的动作。
        // increase方法字节码：
        // 0 getstatic #2 <com/zhoushiya/interview/concurrentDemo/VolatileTest.race>
        // 3 iconst_1
        // 4 iadd
        // 5 putstatic #2 <com/zhoushiya/interview/concurrentDemo/VolatileTest.race>
        // 8 getstatic #3 <com/zhoushiya/interview/concurrentDemo/VolatileTest.atomicInteger>
        //11 invokevirtual #4 <java/util/concurrent/atomic/AtomicInteger.getAndIncrement>
        //14 pop
        //15 return
        // 虽然字节码指令也并不是原子操作，仍然可以看出一些问题
        // getstatic 获取了race的值，此时race值是一致的，假设为1
        // 经过iconst_1 和 iadd ,如果是两个线程并发，很可能出现都为2的结果
        // putstatic 将上面的结果放入race中，就出现了race结果为2，丢失了一个自增操作的问题
        System.out.println(race);

        // atomic类从底层解决了并发问题，不会出现自增丢失的情况
        // 底层是native getAndAddInt
        System.out.println(atomicInteger);  //20000

    }
}
