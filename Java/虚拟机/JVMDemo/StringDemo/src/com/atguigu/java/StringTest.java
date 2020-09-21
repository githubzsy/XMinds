package com.atguigu.java;

import org.junit.Test;

/**
 * @author zhoushiya
 * @date 2020/8/20 11:04
 */
public class StringTest {

    @Test
    public void test3() {
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        // s1+s2 底层是用 new StringBuilder().append().append().toString() 实现的
        String s4 = s1 + s2;
        System.out.println(s3 == s4); //false
    }

    /**
     * 1.字符串拼接操作不一定用StringBuilder
     * 如果连接符号左右两边都是字符串常量或者常量引用，则仍然使用编译期优化
     * 2.能使用final就使用final
     */
    @Test
    public void test4() {
        final String s1 = "a";
        final String s2 = "b";
        String s3 = "ab";
        String s4 = s1 + s2;
        System.out.println(s3 == s4); //true
    }

    public static void main(String[] args) {
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2); //false

        String s3 = new String("1") + new String("1"); //s3=new String("11");
        // 执行完上一行代码后，字符串常量池中是否存在"11"
        // 答案:不存在

        s3.intern(); //在字符串常量池中生成"11"。jdk6:永久代常量池创建了一个新的对象"11",也就有新的地址。jdk7:常量池中并没有创建新的对象"11"，只是记录了s3 "11"的地址。
        String s4 = "11"; //s4记录的地址:上一行常量池中生成的"11"的地址。所以jdk6中,s4是一个全新的地址。jdk7/8中,s4与s3是同一个地址。
        System.out.println(s3 == s4); //jdk6:false jdk7/8:true
    }

    @Test
    public void newTest() {
        /**
         * 对象1:new String("ab")
         * 对象2:常量池"ab"
         */
        String str = new String("ab");

        /**
         * 对象1:StringBuilder匿名对象
         * 对象2:new String("a")
         * 对象3:常量池"a"
         * 对象4:new String("b")
         * 对象5:常量池"b"
         *
         * 深入剖析:StringBuilder的toString():
         * 对象6:new String("ab")
         * 强调一下,toString()的调用，在常量池中，没有生成"ab"。因为toString调用的是char[]。
         */
        String str2 = new String("a") + new String("b");
    }

}
