package com.kuan.tddinterview.springdatajpa.inheritance.singletable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@DiscriminatorValue("blue")
@SuperBuilder
@NoArgsConstructor
public class RedBook extends Book {
    private String redMark;
}
