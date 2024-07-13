package com.kuan.tddinterview.springdatajpa.nplus1.reproduce;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kuan.tddinterview.springdatajpa.nplus1.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@Entity
@Table
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "userInfo")
public class Address extends BaseEntity {

    private String city;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonBackReference
    private UserInfo userInfo;
}