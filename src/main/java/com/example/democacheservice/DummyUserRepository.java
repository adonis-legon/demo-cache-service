package com.example.democacheservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("DummyUserRepository")
public class DummyUserRepository implements UserRepository {

    @Override
    public List<User> getAll() {
        return new ArrayList<>();
    }

    @Override
    public List<User> getActive() {
        return new ArrayList<>();
    }

    @Override
    public User getByName(String name) {
        return new User();
    }

    @Override
    public void addUser(User user) {

    }

}