package com.kuan.tddinterview.springdatajpa.one2one;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OneToOneExtEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

}
