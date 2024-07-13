package com.kuan.tddinterview.springdatajpa.specification.repository;

import com.kuan.tddinterview.springdatajpa.specification.model.PersonAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonAddressRepository extends JpaRepository<PersonAddress, Long> {

}
