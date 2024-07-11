package com.kuan.tddinterview.springdatajpa.inheritance.tablepreclass;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity(name = "people")
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SuperBuilder
@NoArgsConstructor
public class People {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

}
