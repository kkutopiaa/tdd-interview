package com.kuan.tddinterview.springdatajpa.cascade.repository;

import com.kuan.tddinterview.springdatajpa.cascade.many2many.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherEntityRepository extends JpaRepository<TeacherEntity, Long> {

}
