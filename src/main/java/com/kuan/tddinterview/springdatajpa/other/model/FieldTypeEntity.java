package com.kuan.tddinterview.springdatajpa.other.model;

import com.kuan.tddinterview.springdatajpa.other.BaseEntity;
import com.kuan.tddinterview.springdatajpa.other.enumerate.Source;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class FieldTypeEntity extends BaseEntity {

    @Enumerated(value = EnumType.STRING)
    private Source source;

}
