package com.zhoushiya.springDemo.demo5.entity;

/**
 * @author zhoushiya
 * @date 2020/9/15 11:01
 */
public class Account {
    private int id;
    private String userName;
    private float money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", money=" + money +
                '}';
    }
}
