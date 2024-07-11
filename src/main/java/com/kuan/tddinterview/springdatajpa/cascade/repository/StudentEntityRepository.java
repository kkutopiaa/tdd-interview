package com.kuan.tddinterview.springdatajpa.cascade.repository;

import com.kuan.tddinterview.springdatajpa.cascade.one2manyandmany2one.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentEntityRepository extends JpaRepository<StudentEntity, Long> {

}
