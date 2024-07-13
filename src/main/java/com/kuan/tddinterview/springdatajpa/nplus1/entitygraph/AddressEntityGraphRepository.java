package com.kuan.tddinterview.springdatajpa.nplus1.entitygraph;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressEntityGraphRepository extends JpaRepository<AddressEntityGraph, Long> {
}
