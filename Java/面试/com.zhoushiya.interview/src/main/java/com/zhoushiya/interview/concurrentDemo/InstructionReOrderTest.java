package com.zhoushiya.interview.concurrentDemo;

/**
 * 用来演示指令重排
 * 指令重排会发生在两个阶段：
 * 1. 编译期(jvm 加载字节码时)
 * 2. cpu 执行期
 * 但对于单线程来说，不管发生怎样的重排，都必须保持与源代码一致的输出结果（As-If-Serial）.
 * 上述规则保证了单线程的执行结果总是与预期一致，但在多线程的情况，就会出现与预期不一致的情况，
 * 而导致这一情况发生的原因，正是指令重排
 */
public class InstructionReOrderTest {

    Integer a = 0;
    Integer b = 0;
    Integer x = 0;
    Integer y = 0;

    private void showReOrder() throws InterruptedException {

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            a=b=x=y=0;

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 有可能发生重排，即 先执行 x = b,再执行 a = 1
                    a = 1;
                    x = b;

                }
            });

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 有可能发生重排，即先执行 y = a,再执行 b = 1;
                    b = 1;
                    y = a;
                }
            });

            t1.start();
            t2.start();
            t1.join();
            t2.join();
            /**
             * 如果没有指令重排，输出的可以结果为:(0,1)(1,1)(1,0)
             * 但实际上有可能会输出(0,0)
             */
            if (x == 0 && y == 0) {
                System.out.println("第 " + i + "次，x=" + x + ", y=" + y);
                break;
            }
            Thread.sleep(200);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InstructionReOrderTest reOrder = new InstructionReOrderTest();
        reOrder.showReOrder();
    }
}
