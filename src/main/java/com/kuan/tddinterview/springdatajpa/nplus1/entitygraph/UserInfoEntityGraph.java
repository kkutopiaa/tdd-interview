package com.kuan.tddinterview.springdatajpa.nplus1.entitygraph;

import com.kuan.tddinterview.springdatajpa.nplus1.BaseEntity;
import com.kuan.tddinterview.springdatajpa.nplus1.NPlusOneEntity;
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
@NamedEntityGraph(name = "addressGraph", attributeNodes = @NamedAttributeNode(value = "addressList"))
public class UserInfoEntityGraph extends BaseEntity  implements NPlusOneEntity {
    private String name;

    private String telephone;

    @OneToMany(mappedBy = "userInfo", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<AddressEntityGraph> addressList;

}
