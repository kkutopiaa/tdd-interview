package com.kuan.tddinterview.springdatajpa.nplus1;


import com.kuan.tddinterview.springdatajpa.nplus1.batchsize.AddressBatchSize;
import com.kuan.tddinterview.springdatajpa.nplus1.batchsize.UserInfoBatchSize;
import com.kuan.tddinterview.springdatajpa.nplus1.batchsize.UserInfoBatchSizeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.util.List;

import static com.kuan.tddinterview.springdatajpa.nplus1.RedirectLogUtil.*;

@DataJpaTest
public class NPlusOneWithBatchSizeTest {

    @Resource
    UserInfoBatchSizeRepository userInfoBatchSizeRepository;

    @Resource
    EntityManager entityManager;


    @BeforeEach
    public void setup() {
        AddressBatchSize AddressBatchSize1 = AddressBatchSize.builder().city("city-1").build();
        AddressBatchSize AddressBatchSize2 = AddressBatchSize.builder().city("city-2").build();
        List<AddressBatchSize> AddressBatchSizeList1 = List.of(AddressBatchSize1, AddressBatchSize2);

        AddressBatchSize AddressBatchSize11 = AddressBatchSize.builder().city("city-11").build();
        AddressBatchSize AddressBatchSize22 = AddressBatchSize.builder().city("city-22").build();
        List<AddressBatchSize> AddressBatchSizeList2 = List.of(AddressBatchSize11, AddressBatchSize22);

        AddressBatchSize AddressBatchSize111 = AddressBatchSize.builder().city("city-111").build();
        AddressBatchSize AddressBatchSize222 = AddressBatchSize.builder().city("city-222").build();
        List<AddressBatchSize> AddressBatchSizeList3 = List.of(AddressBatchSize111, AddressBatchSize222);

        UserInfoBatchSize user1 = UserInfoBatchSize.builder().name("user-1").telephone("123").addressList(AddressBatchSizeList1).build();
        UserInfoBatchSize user2 = UserInfoBatchSize.builder().name("user-2").telephone("234").addressList(AddressBatchSizeList2).build();
        UserInfoBatchSize user3 = UserInfoBatchSize.builder().name("user-3").telephone("345").addressList(AddressBatchSizeList3).build();

        userInfoBatchSizeRepository.saveAllAndFlush(List.of(user1, user2, user3));
    }


    @Test
    public void should_get_2_times_select_for_display_n_plus_one_problem() {
        entityManager.clear();
        redirectOutputLog(() -> userInfoBatchSizeRepository.findAll());

        // 获取控制台输出
        String consoleOutput = getOutputStream().toString();
        Assertions.assertEquals(2, getSelectTimes(consoleOutput));
    }


    @Test
    public void should_get_address_correctly_with_user_entity() {
        List<UserInfoBatchSize> all = userInfoBatchSizeRepository.findAll();

        Assertions.assertEquals(2, all.get(0).getAddressList().size());
    }


}
