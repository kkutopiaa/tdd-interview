package com.kuan.tddinterview.springdatajpa.inheritance.singletable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@DiscriminatorValue("black")
@SuperBuilder
@NoArgsConstructor
public class BlackBook extends Book {
    private String blackMark;
}
