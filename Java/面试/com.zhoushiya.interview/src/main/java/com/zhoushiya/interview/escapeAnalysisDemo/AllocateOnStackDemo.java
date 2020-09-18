package com.zhoushiya.interview.escapeAnalysisDemo;

import java.time.Instant;

/**
 * 栈上分配demo
 * <p>
 * -server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGCDetails -XX:+EliminateAllocations
 * 其中 -server 是开启 server 模式，逃逸分析需要 server 模式的支持
 * -Xmx10 -Xms10m，设置堆内存是 10m，远小于 1.5G
 * -XX:+DoEscapeAnalysis 开启逃逸分析
 * -XX:+PrintGCDetails 如果发生 gc，打印 gc 日志
 * -XX:+EliminateAllocations 开启标量替换，允许把对象打散分配在栈上，比如 User 对象，它有两个属性 id 和 name，可以把他们看成独立的局部变量分别进行分配
 *
 * @author zhoushiya
 * @date 2020/9/9 12:04
 */
public class AllocateOnStackDemo {
    static class User {

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        int id;

        String name;
    }


    public static void alloc() {
        User user = new User(1, "baiya");
    }

    public static void main(String[] args) {
        long start = Instant.now().toEpochMilli();
        for (int i = 0; i < 100_000_000; i++) {
            alloc();
        }
        long end = Instant.now().toEpochMilli();
        System.out.println("耗时：" + (end - start));
    }
}
