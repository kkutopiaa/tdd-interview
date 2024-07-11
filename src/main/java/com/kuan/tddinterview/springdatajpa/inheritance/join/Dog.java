package com.kuan.tddinterview.springdatajpa.inheritance.join;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "animal_id", referencedColumnName = "id")
public class Dog extends Animal {
    private String play;
}
