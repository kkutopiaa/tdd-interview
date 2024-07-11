package com.kuan.tddinterview.springdatajpa.cascade.one2manyandmany2one;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String cloth;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private ClassEntity classEntity;

}
