package com.kuan.tddinterview.springdatajpa.nplus1.fetchmode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kuan.tddinterview.springdatajpa.nplus1.BaseEntity;
import com.kuan.tddinterview.springdatajpa.nplus1.NPlusOneEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AddressFetchModel extends BaseEntity implements NPlusOneEntity {

    private String city;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonBackReference
    private UserInfoFetchModel userInfo;
}