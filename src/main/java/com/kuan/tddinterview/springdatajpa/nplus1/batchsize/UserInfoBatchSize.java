package com.kuan.tddinterview.springdatajpa.nplus1.batchsize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoBatchSize {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String telephone;

    @BatchSize(size = 20)
    @OneToMany(mappedBy = "userInfo", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<AddressBatchSize> addressList;
}
