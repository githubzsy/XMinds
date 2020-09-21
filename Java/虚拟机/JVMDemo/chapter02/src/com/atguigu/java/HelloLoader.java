package com.atguigu.java;

/**
 * @author zhoushiya
 * @date 2020/7/16 16:00
 */
public class HelloLoader {
    // prepare环节a=0,initial环节才赋值为1
    private static int a = 1;

    static {
        number = 20;
        // 报错:Illegal forward reference(非法的前向引用)
        // System.out.println(number);
    }

    private static int number = 10;

    public static void main(String[] args) {
        int i = 2;
        int m = i++;    // 2
        int n = ++i;    // 4
        System.out.println(m);
        System.out.println(n);

        int a = 5 + 10;
        int b = a + 2;
        int c = a + b;
        // 操作数栈会将a+b的结果先压入栈顶，然后c压入栈顶。最后再add的结果压入栈顶，得到d
        int d = a + b + c;

        // 10
        System.out.println(number);
        ClassLoader classLoader = HelloLoader.class.getClassLoader();
        // sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(classLoader);
        ClassLoader parent = classLoader.getParent();

        // sun.misc.Launcher$ExtClassLoader@1b6d3586
        System.out.println(parent);

        ClassLoader root = parent.getParent();
        // null 引导类加载器无法获取
        System.out.println(root);
    }
}
