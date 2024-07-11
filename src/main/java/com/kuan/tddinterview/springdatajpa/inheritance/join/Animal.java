package com.kuan.tddinterview.springdatajpa.inheritance.join;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity(name="animal")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
@NoArgsConstructor
public class Animal {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
