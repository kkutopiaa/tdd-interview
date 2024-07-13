package com.kuan.tddinterview.springdatajpa.nplus1.fetchmodel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kuan.tddinterview.springdatajpa.nplus1.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@Entity
@Table
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AddressFetchModel extends BaseEntity {

    private String city;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonBackReference
    private UserInfoFetchModel userInfo;
}