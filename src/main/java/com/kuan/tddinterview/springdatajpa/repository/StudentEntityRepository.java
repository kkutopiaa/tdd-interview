package com.kuan.tddinterview.springdatajpa.repository;

import com.kuan.tddinterview.springdatajpa.one2manyandmany2one.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentEntityRepository extends JpaRepository<StudentEntity, Long> {

}
