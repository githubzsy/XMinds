package com.zhoushiya.interview.proxyDemo;

public interface ByeInterface {
    void sayBye();
}
class Bye implements ByeInterface {
    @Override
    public void sayBye() {
        System.out.println("Bye!");
    }
}