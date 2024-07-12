package com.kuan.tddinterview.springboottest.repository;

import com.kuan.tddinterview.springboottest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
