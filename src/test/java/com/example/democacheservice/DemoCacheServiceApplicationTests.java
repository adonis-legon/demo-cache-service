package com.example.democacheservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestInstance(Lifecycle.PER_CLASS)
class DemoCacheServiceApplicationTests {

	@Autowired
	UserService userService;

	@Test
	void whenFindByName_thenReturnUser() {
		User user1 = new User("user1", "user1@domain.com", true);
		userService.createUser(user1);

		final String testUserName = "user1";
		assertEquals(testUserName, userService.findByName(testUserName).getName());
	}

	@Test
	void whenFindByIncorrectName_thenThrowException() {
		final String testUserName = "user3";
		assertThrows(UserMissingException.class, () -> userService.findByName(testUserName));
	}

	@Test
	void whenUserNameIsTooSmall_thenValidationISFalse() {
		User user = new User("user", "user@domain.com", true);
		assertThrows(InvalidUserException.class, () -> userService.createUser(user));
	}
}
