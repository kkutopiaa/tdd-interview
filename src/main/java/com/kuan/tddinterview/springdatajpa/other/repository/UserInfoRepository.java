package com.kuan.tddinterview.springdatajpa.other.repository;

import com.kuan.tddinterview.springdatajpa.other.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
