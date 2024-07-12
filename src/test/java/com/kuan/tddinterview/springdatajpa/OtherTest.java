package com.kuan.tddinterview.springdatajpa;

import com.kuan.tddinterview.springdatajpa.other.JpaConfiguration;
import com.kuan.tddinterview.springdatajpa.other.MyAuditorAware;
import com.kuan.tddinterview.springdatajpa.other.enumerate.Source;
import com.kuan.tddinterview.springdatajpa.other.model.FieldTypeEntity;
import com.kuan.tddinterview.springdatajpa.other.repository.FieldTypeEntityRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Example;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@DataJpaTest

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Import(JpaConfiguration.class)
public class OtherTest {

    @MockBean
    MyAuditorAware myAuditorAware;

    @Resource
    FieldTypeEntityRepository fieldTypeEntityRepository;

    Long expectedAuditorId = 99L;
    private LocalDateTime now;

    @BeforeEach
    public void setup() {
        Mockito.when(myAuditorAware.getCurrentAuditor()).thenReturn(Optional.of(expectedAuditorId));
        FieldTypeEntity entity = new FieldTypeEntity(Source.Web);
        now = LocalDateTime.now();
        fieldTypeEntityRepository.saveAndFlush(entity);
    }

    @Test
    public void should_get_enum_type() {
        FieldTypeEntity entity = fieldTypeEntityRepository.findOne(Example.of(new FieldTypeEntity(Source.Web))).get();

        Assertions.assertEquals(Source.Web, entity.getSource());
    }

    @Test
    public void should_get_auditor_id() {
        FieldTypeEntity entity = fieldTypeEntityRepository.findOne(Example.of(new FieldTypeEntity(Source.Web))).get();

        Assertions.assertEquals(expectedAuditorId, entity.getCreateUserId());
        Assertions.assertEquals(expectedAuditorId, entity.getLastModifiedUserId());
    }


    @Test
    public void should_save_correct_create_time_with_local_date_time() {
        FieldTypeEntity entity = fieldTypeEntityRepository.findOne(Example.of(new FieldTypeEntity(Source.Web))).get();

        Assertions.assertTrue(ChronoUnit.MINUTES.between(now, entity.getCreateTime()) < 1);
    }

    @Test
    public void should_save_correct_create_time_and_last_modified_time_with_local_date_time_by_first_storage() {
        FieldTypeEntity entity = fieldTypeEntityRepository.findOne(Example.of(new FieldTypeEntity(Source.Web))).get();

        Assertions.assertEquals(entity.getCreateTime(), entity.getLastModifiedTime());
    }


    @Test
    public void should_save_last_modified_time_with_local_date_time() throws InterruptedException {
        FieldTypeEntity entity = fieldTypeEntityRepository.findOne(Example.of(new FieldTypeEntity(Source.Web))).get();
        entity.setSource(Source.Other);
        Thread.sleep(100);
        fieldTypeEntityRepository.saveAndFlush(entity);

        FieldTypeEntity target = fieldTypeEntityRepository.findOne(Example.of(new FieldTypeEntity(Source.Other))).get();

        Assertions.assertTrue(target.getLastModifiedTime().isAfter(target.getCreateTime()));
    }











}
