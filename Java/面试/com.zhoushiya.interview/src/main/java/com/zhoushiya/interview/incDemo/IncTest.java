package com.zhoushiya.interview.incDemo;

import org.junit.Test;

/**
 * 自增研究
 *
 * @author zhoushiya
 * @date 2020/9/14 16:22
 */
public class IncTest {
    @Test
    public void test1() {
        int i = 1;
        int a = i++;
        System.out.println(a);
    }

    @Test
    public void test2() {
        int i = 1;
        int a = ++i;
        System.out.println(a);
    }

    @Test
    public void test3() {
        int i = 1;
        i = i++;
        System.out.println(i);
    }
}
