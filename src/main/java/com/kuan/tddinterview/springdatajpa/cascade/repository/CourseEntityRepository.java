package com.kuan.tddinterview.springdatajpa.cascade.repository;

import com.kuan.tddinterview.springdatajpa.cascade.many2many.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseEntityRepository extends JpaRepository<CourseEntity, Long> {

}
