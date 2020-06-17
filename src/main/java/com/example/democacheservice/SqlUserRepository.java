package com.example.democacheservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("SqlUserRepository")
public class SqlUserRepository implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getAll() {
        final String GET_USERS = "select * from user";
        return jdbcTemplate.query(GET_USERS,
                (rs, rowNum) -> new User(rs.getString("name"), rs.getString("email"), rs.getBoolean("active")));
    }

    @Override
    public List<User> getActive() {
        final String GET_ACTIVE_USERS = "select * from user where active = ?";
        return jdbcTemplate.query(GET_ACTIVE_USERS, new Object[] { true },
                (rs, rowNum) -> new User(rs.getString("name"), rs.getString("email"), rs.getBoolean("active")));
    }

    @Override
    public User getByName(String name) {
        final String GET_USER_BY_NAME = "select * from user where name = ?";
        List<User> result = jdbcTemplate.query(GET_USER_BY_NAME, new Object[] { name },
                (rs, rowNum) -> new User(rs.getString("name"), rs.getString("email"), rs.getBoolean("active")));

        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public void addUser(User user) {
        final String INSERT_USER = "insert into user(name, email, active) values(?, ?, ?)";
        jdbcTemplate.update(INSERT_USER, user.getName(), user.getEmail(), user.getActive());
    }

}