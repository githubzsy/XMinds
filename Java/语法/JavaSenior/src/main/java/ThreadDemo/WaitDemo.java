package ThreadDemo;

/**
 * @author zhoushiya
 * @date 2020/9/19 17:15
 */
public class WaitDemo {

    static int i = 1;

    static Object lock = new Object();

    final static int THREAD_COUNT = 2;

    final static int MAX = 100;

    public static void main(String[] args) {
        Runnable runnable = () -> {
            while (i < MAX + 1) {
                synchronized (lock) {
                    // 使用双重检查，此处若使用volatile 并不能保证i的一致性
                    if (i < MAX + 1) {
                        System.out.println(Thread.currentThread().getName() + ":" + i++);
                        lock.notifyAll();
                        // 线程数大于1且i没有达到100，当前线程需要wait给其它线程操作
                        if (THREAD_COUNT > 1 && i < MAX) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        // Thread.sleep(100);
                    }
                }
            }
        };

        for (int j = 0; j < THREAD_COUNT; j++) {
            new Thread(runnable).start();
        }
    }
}
