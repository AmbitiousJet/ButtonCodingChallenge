package com.qc.mvpbase.model;

/**
 * Created by mohammadnaz on 3/1/18.
 */

public class User {
    public String name;
    public String email;
    public String candidate;

    public User(String name, String email, String candidate) {
        this.name = name;
        this.email = email;
        this.candidate = candidate;
    }
}
