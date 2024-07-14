package com.kuan.tddinterview.springdatajpa.nplus1;

import com.kuan.tddinterview.springdatajpa.nplus1.reproduce.AddressRepository;
import com.kuan.tddinterview.springdatajpa.nplus1.reproduce.UserInfoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

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
        UserGenerator.save(userInfoRepository, Type.REPRODUCE);
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
