package com.kuan.tddinterview.springdatajpa.specification.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PersonAddress {

    @Id
    @GeneratedValue
    private Long id;

    private String address;

    @ManyToOne(cascade = CascadeType.ALL)
    private Person person;

}
