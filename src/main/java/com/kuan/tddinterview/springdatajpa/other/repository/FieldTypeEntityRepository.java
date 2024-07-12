package com.kuan.tddinterview.springdatajpa.other.repository;

import com.kuan.tddinterview.springdatajpa.other.model.FieldTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldTypeEntityRepository extends JpaRepository<FieldTypeEntity, Long> {
}
