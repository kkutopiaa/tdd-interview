package com.kuan.tddinterview.springdatajpa.nplus1.reproduce;

import com.kuan.tddinterview.springdatajpa.nplus1.BaseEntity;
import com.kuan.tddinterview.springdatajpa.nplus1.NPlusOneEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table
@ToString(exclude = "addressList")
public class UserInfo extends BaseEntity implements NPlusOneEntity {
    private String name;

    private String telephone;

    @OneToMany(mappedBy = "userInfo", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Address> addressList;
}
