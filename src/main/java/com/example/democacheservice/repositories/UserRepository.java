package com.example.democacheservice.repositories;

import java.util.List;

import com.example.democacheservice.domain.User;

public interface UserRepository {
    List<User> getAll();

    List<User> getActive();

    User getByName(String name);

    void addUser(User user);
}