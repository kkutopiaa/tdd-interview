package com.kuan.tddinterview.springboottest;

import com.kuan.tddinterview.springboottest.model.User;
import com.kuan.tddinterview.springboottest.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.annotation.Resource;

@DataJpaTest
public class UserRepositoryTest {

    @Resource
    TestEntityManager testEntityManager;

    @Resource
    UserRepository userRepository;

    @BeforeEach
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void givenFindByUserName_whenUserExists_ThenReturnsUser() {
        User user = User.builder().username("test-user").displayName("test-display").password("passw0rd").build();
        testEntityManager.persist(user);

        User inDB = userRepository.findByUsername("test-user");
        Assertions.assertNotNull(inDB);
    }

    @Test
    public void givenFindByUsername_whenUserDoesNotExist_returnsNull() {
        User inDB = userRepository.findByUsername("test-user");
        Assertions.assertNull(inDB);
    }
}
