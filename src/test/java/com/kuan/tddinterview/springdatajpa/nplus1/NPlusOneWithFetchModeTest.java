package com.kuan.tddinterview.springdatajpa.nplus1;


import com.kuan.tddinterview.springdatajpa.nplus1.fetchmodel.AddressFetchModel;
import com.kuan.tddinterview.springdatajpa.nplus1.fetchmodel.UserInfoFetchModel;
import com.kuan.tddinterview.springdatajpa.nplus1.fetchmodel.UserInfoFetchModelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.util.List;

import static com.kuan.tddinterview.springdatajpa.nplus1.RedirectLogUtil.*;

@DataJpaTest
public class NPlusOneWithFetchModeTest {

    @Resource
    UserInfoFetchModelRepository userInfoFetchModelRepository;

    @Resource
    EntityManager entityManager;


    @BeforeEach
    public void setup() {
        AddressFetchModel AddressFetchModel1 = AddressFetchModel.builder().city("city-1").build();
        AddressFetchModel AddressFetchModel2 = AddressFetchModel.builder().city("city-2").build();
        List<AddressFetchModel> AddressFetchModelList1 = List.of(AddressFetchModel1, AddressFetchModel2);

        AddressFetchModel AddressFetchModel11 = AddressFetchModel.builder().city("city-11").build();
        AddressFetchModel AddressFetchModel22 = AddressFetchModel.builder().city("city-22").build();
        List<AddressFetchModel> AddressFetchModelList2 = List.of(AddressFetchModel11, AddressFetchModel22);

        AddressFetchModel AddressFetchModel111 = AddressFetchModel.builder().city("city-111").build();
        AddressFetchModel AddressFetchModel222 = AddressFetchModel.builder().city("city-222").build();
        List<AddressFetchModel> AddressFetchModelList3 = List.of(AddressFetchModel111, AddressFetchModel222);

        UserInfoFetchModel user1 = UserInfoFetchModel.builder().name("user-1").telephone("123").addressList(AddressFetchModelList1).build();
        UserInfoFetchModel user2 = UserInfoFetchModel.builder().name("user-2").telephone("234").addressList(AddressFetchModelList2).build();
        UserInfoFetchModel user3 = UserInfoFetchModel.builder().name("user-3").telephone("345").addressList(AddressFetchModelList3).build();

        userInfoFetchModelRepository.saveAllAndFlush(List.of(user1, user2, user3));
    }


    @Test
    public void should_get_2_times_select_for_display_n_plus_one_problem() {
        entityManager.clear();
        redirectOutputLog(() -> userInfoFetchModelRepository.findAll());

        // 获取控制台输出
        String consoleOutput = getOutputStream().toString();
        Assertions.assertEquals(2, getSelectTimes(consoleOutput));
    }


    @Test
    public void should_get_address_correctly_with_user_entity() {
        List<UserInfoFetchModel> all = userInfoFetchModelRepository.findAll();

        Assertions.assertEquals(2, all.get(0).getAddressList().size());
    }


}
