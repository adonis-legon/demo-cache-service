package com.example.democacheservice;

import java.util.List;
import java.util.Optional;

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
            throw new UserMissingException();
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