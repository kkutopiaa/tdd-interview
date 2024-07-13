package com.kuan.tddinterview.springdatajpa.nplus1;


import com.kuan.tddinterview.springdatajpa.nplus1.entitygraph.AddressEntityGraph;
import com.kuan.tddinterview.springdatajpa.nplus1.entitygraph.UserInfoEntityGraph;
import com.kuan.tddinterview.springdatajpa.nplus1.entitygraph.UserInfoEntityGraphRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.util.List;

import static com.kuan.tddinterview.springdatajpa.nplus1.RedirectLogUtil.*;

@DataJpaTest
public class NPlusOneWithEntityGraphTest {

    @Resource
    UserInfoEntityGraphRepository userInfoEntityGraphRepository;

    @Resource
    EntityManager entityManager;


    @BeforeEach
    public void setup() {
        AddressEntityGraph AddressEntityGraph1 = AddressEntityGraph.builder().city("city-1").build();
        AddressEntityGraph AddressEntityGraph2 = AddressEntityGraph.builder().city("city-2").build();
        List<AddressEntityGraph> AddressEntityGraphList1 = List.of(AddressEntityGraph1, AddressEntityGraph2);

        AddressEntityGraph AddressEntityGraph11 = AddressEntityGraph.builder().city("city-11").build();
        AddressEntityGraph AddressEntityGraph22 = AddressEntityGraph.builder().city("city-22").build();
        List<AddressEntityGraph> AddressEntityGraphList2 = List.of(AddressEntityGraph11, AddressEntityGraph22);

        AddressEntityGraph AddressEntityGraph111 = AddressEntityGraph.builder().city("city-111").build();
        AddressEntityGraph AddressEntityGraph222 = AddressEntityGraph.builder().city("city-222").build();
        List<AddressEntityGraph> AddressEntityGraphList3 = List.of(AddressEntityGraph111, AddressEntityGraph222);

        UserInfoEntityGraph user1 = UserInfoEntityGraph.builder().name("user-1").telephone("123").addressList(AddressEntityGraphList1).build();
        UserInfoEntityGraph user2 = UserInfoEntityGraph.builder().name("user-2").telephone("234").addressList(AddressEntityGraphList2).build();
        UserInfoEntityGraph user3 = UserInfoEntityGraph.builder().name("user-3").telephone("345").addressList(AddressEntityGraphList3).build();

        userInfoEntityGraphRepository.saveAllAndFlush(List.of(user1, user2, user3));
    }


    @Test
    public void should_get_2_times_select_for_display_n_plus_one_problem() {
        entityManager.clear();
        redirectOutputLog(() -> userInfoEntityGraphRepository.findAll());

        // 获取控制台输出
        String consoleOutput = getOutputStream().toString();
        Assertions.assertEquals(1, getSelectTimes(consoleOutput));
    }


    @Test
    public void should_get_address_correctly_with_user_entity() {
        List<UserInfoEntityGraph> all = userInfoEntityGraphRepository.findAll();

        Assertions.assertEquals(2, all.get(0).getAddressList().size());
    }


}
