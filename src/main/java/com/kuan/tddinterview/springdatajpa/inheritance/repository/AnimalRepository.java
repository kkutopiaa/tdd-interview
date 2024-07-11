package com.kuan.tddinterview.springdatajpa.inheritance.repository;

import com.kuan.tddinterview.springdatajpa.inheritance.join.Animal;
import com.kuan.tddinterview.springdatajpa.inheritance.join.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal,Long> {
}
