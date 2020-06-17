package com.example.democacheservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {
    @Value("${app.repositories.user.qualifier}")
    String repositoryQualifier;

    @Autowired
    SqlUserRepository sqlUserRepository;

    @Autowired
    DummyUserRepository dummyUserRepository;

    @Bean
    public UserRepository userRepository() throws Exception {
        switch (repositoryQualifier) {
            case "SqlUserRepository":
                return sqlUserRepository;
            case "DummyUserRepository":
                return dummyUserRepository;
            default:
                throw new Exception("Invalid User Repository Qualifier: " + repositoryQualifier);
        }
    }
}