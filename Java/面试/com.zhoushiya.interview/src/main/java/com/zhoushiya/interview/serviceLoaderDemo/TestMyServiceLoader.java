package com.zhoushiya.interview.serviceLoaderDemo;

import java.util.Iterator;
import java.util.ServiceLoader;

public class TestMyServiceLoader {
    public static void main(String[] argus){
        ServiceLoader<IMyServiceLoader> serviceLoader = ServiceLoader.load(IMyServiceLoader.class);
        Iterator<IMyServiceLoader> iterator = serviceLoader.iterator();

        // 与下面的for循环效果是一样的
//        while (iterator.hasNext()){
//            iterator.next().sayHello();
//        }
        for (IMyServiceLoader myServiceLoader : serviceLoader){
            System.out.println(myServiceLoader.getName() + myServiceLoader.sayHello());
        }
    }
}