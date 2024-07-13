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
import java.util.function.BiFunction;

import static com.kuan.tddinterview.springdatajpa.nplus1.RedirectLogUtil.*;

@DataJpaTest
public class NPlusOneWithBatchSizeTest {

    @Resource
    UserInfoBatchSizeRepository userInfoBatchSizeRepository;

    @Resource
    EntityManager entityManager;

    @BeforeEach
    public void setup() {
        UserGenerator.save(userInfoBatchSizeRepository, UserGenerator.Type.BATCH_SIZE);
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
