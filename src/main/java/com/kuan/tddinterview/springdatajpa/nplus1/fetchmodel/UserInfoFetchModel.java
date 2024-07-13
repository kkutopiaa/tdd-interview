package com.kuan.tddinterview.springdatajpa.nplus1.fetchmodel;

import com.kuan.tddinterview.springdatajpa.nplus1.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class UserInfoFetchModel extends BaseEntity {
    private String name;

    private String telephone;

    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "userInfo", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<AddressFetchModel> addressList;

}
