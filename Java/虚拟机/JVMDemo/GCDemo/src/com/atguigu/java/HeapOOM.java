package com.atguigu.java;

import java.util.ArrayList;

/**
 * -Xms8m -Xmx8m -XX:+HeapDumpOnOutMemoryError
 * @author zhoushiya
 * @date 2020/8/24 17:04
 */
public class HeapOOM {
    byte[] buffer=new byte[1*1024*1024];

    public static void main(String[] args) {
        ArrayList<HeapOOM> list=new ArrayList<>();

        int count=0;
        try {
            while (true) {
                list.add(new HeapOOM());
                count++;
            }
        }
        catch (Throwable e){
            throw  e;
        }
        finally {
            System.out.println(count);
        }
    }
}
