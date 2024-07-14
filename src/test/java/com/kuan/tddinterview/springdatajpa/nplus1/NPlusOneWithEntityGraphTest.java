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
        UserGenerator.save(userInfoEntityGraphRepository, UserGenerator.Type.ENTITY_GRAPH);
    }

    @Test
    public void should_get_2_times_select_for_resolve_n_plus_one_problem() {
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
