package com.kuan.tddinterview.springdatajpa.repository;

import com.kuan.tddinterview.springdatajpa.one2one.OneToOneExtEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OneToOneExtEntityRepository extends JpaRepository<OneToOneExtEntity, Long> {

}
