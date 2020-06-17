package com.example.democacheservice;

import java.util.List;

public interface UserRepository {
    List<User> getAll();

    List<User> getActive();

    User getByName(String name);

    void addUser(User user);
}