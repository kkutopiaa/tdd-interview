package com.kuan.tddinterview.springdatajpa.nplus1;

import com.kuan.tddinterview.springdatajpa.nplus1.reproduce.Address;
import com.kuan.tddinterview.springdatajpa.nplus1.reproduce.UserInfo;
import com.kuan.tddinterview.springdatajpa.nplus1.reproduce.AddressRepository;
import com.kuan.tddinterview.springdatajpa.nplus1.reproduce.UserInfoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.util.List;

import static com.kuan.tddinterview.springdatajpa.nplus1.RedirectLogUtil.*;

@DataJpaTest
public class NPlusOneReproduceTest {

    @Resource
    UserInfoRepository userInfoRepository;

    @Resource
    AddressRepository addressRepository;

    @Resource
    EntityManager entityManager;


    @BeforeEach
    public void setup() {
        Address address1 = Address.builder().city("city-1").build();
        Address address2 = Address.builder().city("city-2").build();
        List<Address> addressList1 = List.of(address1, address2);

        Address address11 = Address.builder().city("city-11").build();
        Address address22 = Address.builder().city("city-22").build();
        List<Address> addressList2 = List.of(address11, address22);

        Address address111 = Address.builder().city("city-111").build();
        Address address222 = Address.builder().city("city-222").build();
        List<Address> addressList3 = List.of(address111, address222);

        UserInfo user1 = UserInfo.builder().name("user-1").telephone("123").addressList(addressList1).build();
        UserInfo user2 = UserInfo.builder().name("user-2").telephone("234").addressList(addressList2).build();
        UserInfo user3 = UserInfo.builder().name("user-3").telephone("345").addressList(addressList3).build();

        userInfoRepository.saveAllAndFlush(List.of(user1, user2, user3));
    }


    @Test
    public void should_initial_data_correctly() {
        Assertions.assertEquals(3, userInfoRepository.findAll().size());
        Assertions.assertEquals(6, addressRepository.findAll().size());
    }


    @Test
    public void should_get_4_times_select_for_display_n_plus_one_problem() {
        entityManager.clear();
        redirectOutputLog(() -> userInfoRepository.findAll());

        // 获取控制台输出
        String consoleOutput = getOutputStream().toString();
        Assertions.assertEquals(4, getSelectTimes(consoleOutput));
    }


}
