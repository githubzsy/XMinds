package com.zhoushiya.interview.finallyDemo;

public class TestTryCatch {
    public static void main(String[] args) {
        TestTryCatch test = new TestTryCatch();
        System.out.println(test.fun());
    }

    public StringBuilder fun() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            System.out.println("try");
            stringBuilder.append("result:try");
            int i = 1 / 0;
            return stringBuilder.append(" try2");
        } catch (Exception e) {
            System.out.println("catch");
            stringBuilder.append(" result:catch");
            return stringBuilder.append(" catch2");
        } finally {
            System.out.println("finally");
            stringBuilder.append(" result:finally");
            return stringBuilder.append(" finally2");
        }
    }
}