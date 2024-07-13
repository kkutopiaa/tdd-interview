package com.kuan.tddinterview.springdatajpa.nplus1.entitygraph;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoEntityGraphRepository extends JpaRepository<UserInfoEntityGraph, Long> {

    @Override
    @EntityGraph(value = "addressGraph", type = EntityGraph.EntityGraphType.LOAD)
    List<UserInfoEntityGraph> findAll();
}
