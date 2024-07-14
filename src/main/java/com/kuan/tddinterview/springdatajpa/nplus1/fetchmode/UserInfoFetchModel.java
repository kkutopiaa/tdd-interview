package com.kuan.tddinterview.springdatajpa.nplus1.fetchmode;

import com.kuan.tddinterview.springdatajpa.nplus1.BaseEntity;
import com.kuan.tddinterview.springdatajpa.nplus1.NPlusOneEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class UserInfoFetchModel extends BaseEntity implements NPlusOneEntity {
    private String name;

    private String telephone;

    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "userInfo", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<AddressFetchModel> addressList;

}
