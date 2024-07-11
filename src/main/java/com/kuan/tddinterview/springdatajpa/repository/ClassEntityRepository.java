package com.kuan.tddinterview.springdatajpa.repository;

import com.kuan.tddinterview.springdatajpa.one2manyandmany2one.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassEntityRepository extends JpaRepository<ClassEntity, Long> {

}