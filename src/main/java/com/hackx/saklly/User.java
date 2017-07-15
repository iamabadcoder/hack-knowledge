package com.hackx.saklly;

import java.io.Serializable;

/**
 * Created by 曹磊(Hackx) on 11/7/2017.
 * Email: caolei@mobike.com
 */
public class User implements Serializable {

    private String username;

    private int age;

    public User(String username,int age){
        this.username = username;
        this.age =age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "username:"+username+",age:"+age;
    }
}
