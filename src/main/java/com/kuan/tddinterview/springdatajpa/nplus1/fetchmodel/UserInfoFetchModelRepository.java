package com.kuan.tddinterview.springdatajpa.nplus1.fetchmodel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoFetchModelRepository extends JpaRepository<UserInfoFetchModel, Long> {
}
