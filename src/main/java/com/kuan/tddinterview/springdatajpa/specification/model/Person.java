package com.kuan.tddinterview.springdatajpa.specification.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kuan.tddinterview.springdatajpa.specification.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private SexEnum sex;

    private Integer age;

    private Instant createDate;

    private Date updateDate;

    @OneToMany(mappedBy = "person")
    @JsonIgnore
    private List<PersonAddress> addresses;

}
