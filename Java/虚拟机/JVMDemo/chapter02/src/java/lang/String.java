package java.lang;

/**
 * @author zhoushiya
 * @date 2020/7/16 20:28
 */
public class String {
    static {
        // 永远不会执行，因为双亲委派机制总是加载的核心类中的String
        System.out.println("自定义的String类的静态代码块");
    }

    /**
     * 永远不会执行，因为双亲委派机制总是加载的核心类中的String，而核心类String并没有main方法
     * @param args
     */
    public static void main(String[] args) {
        /**
         * 错误: 在类 java.lang.String 中找不到 main 方法, 请将 main 方法定义为:
         *    public static void main(String[] args)
         * 否则 JavaFX 应用程序类必须扩展javafx.application.Application
         */
        System.out.println("自定义String类的main方法");
    }
}
