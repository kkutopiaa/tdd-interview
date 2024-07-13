package com.kuan.tddinterview.springdatajpa.nplus1.batchsize;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoBatchSizeRepository extends JpaRepository<UserInfoBatchSize, Long> {
}
