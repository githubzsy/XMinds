package com.zhoushiya.springDemo.demo2.collectionType;

import java.util.List;

/**
 * @author zhoushiya
 * @date 2020/9/11 18:00
 */
public class Book {
    private List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Book{" +
                "list=" + list +
                '}';
    }
}
