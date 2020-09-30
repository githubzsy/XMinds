/**
 * @author zhoushiya
 * @date 2020/9/30 11:48
 */
public class ThreadLocalDemo {
    public static void main(String[] args) {
        /**
         * 实际上就是操作CurrentThread.ThreadLocalMap
         * ThreadLocalMap key:当前ThreadLocal对象 value:具体值
         */
        ThreadLocal<Integer> a = new ThreadLocal<>();
        a.set(1);
    }
}
