package com.atguigu.java;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author zhoushiya
 * @date 2020/8/29 13:35
 */
public class PhantomReferenceDemo {
    public static PhantomReferenceDemo obj;
    static ReferenceQueue<PhantomReferenceDemo> queue = null;

    public static class CheckRefQueue extends Thread {
        @Override
        public void run() {
            while (true) {
                if (queue != null) {
                    PhantomReference<PhantomReferenceDemo> objt = null;
                    try {
                        objt = (PhantomReference<PhantomReferenceDemo>) queue.remove();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 当虚引用可以从队列中取出时，说明实例被垃圾回收了
                    if (objt != null) {
                        System.out.println("追踪垃圾回收过程：PhantomReferenceDemo实例被GC了");
                    }
                }
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("调用当前类的finalize()方法");
        obj = this;
    }

    public static void main(String[] args) {
        Thread t = new CheckRefQueue();
        t.setDaemon(true); //设置为守护线程：当程序中没有非守护线程时，守护线程也就执行结束。
        t.start();

        queue = new ReferenceQueue<PhantomReferenceDemo>();
        obj = new PhantomReferenceDemo();

        // 构造了PhantomReferenceDemo 对象的虚引用，并制定了引用队列
        PhantomReference<PhantomReferenceDemo> phantomRef = new PhantomReference<>(obj, queue);

        // 不可获取虚引用中的对象
        System.out.println(phantomRef.get());

        try {

            System.out.println("第一次GC");
            //将强引用去除
            obj = null;
            //第一次进行GC，由于对象可复活，GC无法回收该对象
            System.gc();
            Thread.sleep(1000);
            if (obj == null) {
                System.out.println("obj 是 null");
            } else {
                System.out.println("obj 可用");
            }
            System.out.println("第二次GC");
            obj = null;
            System.gc();    //一旦将obj对象回收，就会将此虚引用存放到引用队列中
            Thread.sleep(1000);
            if (obj == null) {
                System.out.println("obj 是 null");
            } else {
                System.out.println("obj 可用");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
