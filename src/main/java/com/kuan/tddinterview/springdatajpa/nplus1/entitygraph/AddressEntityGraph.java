package com.kuan.tddinterview.springdatajpa.nplus1.entitygraph;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kuan.tddinterview.springdatajpa.nplus1.BaseEntity;
import com.kuan.tddinterview.springdatajpa.nplus1.NPlusOneEntity;
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
@NamedEntityGraph(name = "getAllUserInfo", attributeNodes = @NamedAttributeNode(value = "userInfo"))
public class AddressEntityGraph extends BaseEntity implements NPlusOneEntity {

    private String city;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonBackReference
    private UserInfoEntityGraph userInfo;
}