package com.example.democacheservice;

public class User {
    String name;
    String email;
    Boolean active;

    public User() {
    }

    public User(String name, String email, Boolean active) {
        this.name = name;
        this.email = email;
        this.active = active;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}