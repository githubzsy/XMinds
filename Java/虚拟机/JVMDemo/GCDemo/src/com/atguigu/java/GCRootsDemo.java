package com.atguigu.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author zhoushiya
 * @date 2020/8/24 16:21
 */
public class GCRootsDemo {
    public static void main(String[] args) {
        List<Object> numList=new ArrayList<>();
        Object objA=new Object();
        for (int i = 0; i < 100; i++) {
            numList.add(i);
        }

        System.out.println("数据添加完毕，请操作：");
        new Scanner(System.in).next();
        objA=null;
        numList=null;
        System.out.println("objA、numList已置空，请操作：");
        new Scanner(System.in).next();
        System.out.println("结束");
    }
}
