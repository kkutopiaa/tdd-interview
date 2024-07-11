package com.kuan.tddinterview.springdatajpa.many2many;

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
public class CourseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<TeacherEntity> teachers;


}
