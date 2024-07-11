package com.kuan.tddinterview.springdatajpa.inheritance.repository;

import com.kuan.tddinterview.springdatajpa.inheritance.singletable.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}
