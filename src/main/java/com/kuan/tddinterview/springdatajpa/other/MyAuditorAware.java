package com.kuan.tddinterview.springdatajpa.other;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class MyAuditorAware implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        return Optional.of(99L);
    }
}
