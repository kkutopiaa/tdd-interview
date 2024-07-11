package com.kuan.tddinterview.springdatajpa.cascade.many2many;

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
public class TeacherEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "teachers", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<CourseEntity> courses;

}
