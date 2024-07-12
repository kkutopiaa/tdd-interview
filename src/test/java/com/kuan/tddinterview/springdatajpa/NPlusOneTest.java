package com.kuan.tddinterview.springdatajpa;

import com.kuan.tddinterview.springdatajpa.other.model.Address;
import com.kuan.tddinterview.springdatajpa.other.model.UserInfo;
import com.kuan.tddinterview.springdatajpa.other.repository.AddressRepository;
import com.kuan.tddinterview.springdatajpa.other.repository.UserInfoRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.annotation.Resource;
import java.util.List;

@DataJpaTest
public class NPlusOneTest {

    @Resource
    UserInfoRepository userInfoRepository;

    @Resource
    AddressRepository addressRepository;


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
    public void should_init() {
        Assertions.assertEquals(3, userInfoRepository.findAll().size());
        Assertions.assertEquals(6, addressRepository.findAll().size());
    }


    @Test
    public void s() {
        List<UserInfo> all = userInfoRepository.findAll();
        System.out.println(all);
    }


}
