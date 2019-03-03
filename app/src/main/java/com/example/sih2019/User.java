package com.example.sih2019;

public class User {
    String name;
    String email;
    String no;

    public User(String name, String email, String no) {
        this.name = name;
        this.email = email;
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getNo() {
        return no;
    }


    public User(){

    }
}

