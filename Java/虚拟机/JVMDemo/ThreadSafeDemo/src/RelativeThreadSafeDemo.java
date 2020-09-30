import java.util.Vector;

/**
 * 尽管Vector.get()、remove()、size()方法都是同步的，但是多线程下不采取同步措施，以下代码仍然不是线程安全的。
 * 对于以下代码：size()只能保证每次获取的返回值是准确的，但不能保证当前的索引位置一定存在
 * 例如i=9时，刚好要打印第10个元素，刚好第10个元素被移除了，这时就会报错
 * 加上了synchronized(vector)保证了移除或者打印，两者只能运行其中一个，这就不会越界了
 * @author zhoushiya
 * @date 2020/9/30 11:16
 */
public class RelativeThreadSafeDemo {
    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread removeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    //synchronized (vector) {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                    //}
                }
            });

            Thread printThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    // synchronized (vector) {
                    for (int i = 0; i < vector.size(); i++) {
                        System.out.println(vector.get(i));
                    }
                    // }
                }
            });

            removeThread.start();
            printThread.start();

            while (Thread.activeCount()>20);
        }

    }
}
