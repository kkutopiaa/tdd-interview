package com.kuan.tddinterview.springdatajpa.inheritance.repository;

import com.kuan.tddinterview.springdatajpa.inheritance.tablepreclass.Female;
import com.kuan.tddinterview.springdatajpa.inheritance.tablepreclass.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FemaleRepository extends JpaRepository<Female,Long> {
}
