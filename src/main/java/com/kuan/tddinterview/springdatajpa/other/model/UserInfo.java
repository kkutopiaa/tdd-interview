package com.kuan.tddinterview.springdatajpa.other.model;

import com.kuan.tddinterview.springdatajpa.other.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table
@ToString(exclude = "addressList")
public class UserInfo extends BaseEntity {
    private String name;

    private String telephone;

    @OneToMany(mappedBy = "userInfo", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Address> addressList;
}
