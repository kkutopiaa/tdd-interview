package com.kuan.tddinterview.springdatajpa.cascade.one2manyandmany2one;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "classEntity", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<StudentEntity> students;

}
