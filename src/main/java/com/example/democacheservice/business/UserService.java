package com.example.democacheservice.business;

import java.util.List;
import java.util.Optional;

import com.example.democacheservice.domain.User;
import com.example.democacheservice.exceptions.InvalidUserException;
import com.example.democacheservice.exceptions.MissingUserException;
import com.example.democacheservice.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

@Service
@EnableCaching
@CacheConfig(cacheNames = { "users" })
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findByName(String name) {
        Optional<User> user = findAll().stream().filter(u -> u.getName().equals(name)).findFirst();

        if (!user.isPresent()) {
            throw new MissingUserException();
        }

        return user.get();
    }

    @Cacheable
    public List<User> findAll() {
        return userRepository.getAll();
    }

    public void createUser(User user) {
        if (!validateUser(user)) {
            throw new InvalidUserException();
        }
        userRepository.addUser(user);
    }

    public Boolean validateUser(User user) {
        return user.getName().length() > 4 && user.getName().length() <= 20;
    }
}