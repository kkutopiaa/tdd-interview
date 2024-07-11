package com.kuan.tddinterview.springdatajpa.one2one;


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
public class OneToOneEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY, optional = true)
    private OneToOneExtEntity oneToOneExtEntity;

}
