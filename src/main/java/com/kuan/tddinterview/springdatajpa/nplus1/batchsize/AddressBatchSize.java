package com.kuan.tddinterview.springdatajpa.nplus1.batchsize;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kuan.tddinterview.springdatajpa.nplus1.NPlusOneEntity;
import com.kuan.tddinterview.springdatajpa.other.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressBatchSize  implements NPlusOneEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String city;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JsonBackReference
    private UserInfoBatchSize userInfo;
}