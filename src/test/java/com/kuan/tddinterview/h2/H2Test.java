package com.kuan.tddinterview.h2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

@DataJpaTest
public class H2Test {

    @Resource
    EntityManager entityManager;

    @Test
    public void should_get_exception() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            entityManager.createQuery("select aa from a").getResultList();
        });
    }


}
