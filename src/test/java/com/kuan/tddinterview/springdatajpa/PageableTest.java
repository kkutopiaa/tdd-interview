package com.kuan.tddinterview.springdatajpa;

import com.kuan.tddinterview.springdatajpa.specification.SexEnum;
import com.kuan.tddinterview.springdatajpa.specification.model.Person;
import com.kuan.tddinterview.springdatajpa.specification.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@DataJpaTest
public class PageableTest {

    @Resource
    PersonRepository personRepository;


    @BeforeEach
    public void setup() {
        Person person1 = Person.builder()
                .name("qxk")
                .email("123456@163.com")
                .sex(SexEnum.MAN)
                .age(18)
                .createDate(Instant.now())
                .updateDate(new Date())
                .build();
        Person person2 = Person.builder()
                .name("jack")
                .email("23456@163.com")
                .sex(SexEnum.FEMALE)
                .age(20)
                .createDate(Instant.now().minusMillis(2000))
                .updateDate(new Date())
                .build();

        personRepository.saveAllAndFlush(List.of(person1, person2));
    }

    @Test
    public void should_get_one_entity_with_size_of_one() {
        Page<Person> all = personRepository.findAll(PageRequest.of(0, 1));
        Assertions.assertEquals(1, all.getContent().size());
    }

    @ParameterizedTest
    @CsvSource(value = {"2", "3", "10", "20"})
    public void should_get_all_entity_with_size_greater_and_equal_two(Integer size) {
        Page<Person> all = personRepository.findAll(PageRequest.of(0, size));
        Assertions.assertEquals(2, all.getContent().size());
    }


    @ParameterizedTest
    @CsvSource(value = {"1,2", "1,3", "3,1", "2,2"})
    public void should_get_empty_entity_with_invalid_page_and_size(Integer page, Integer size) {
        Page<Person> all = personRepository.findAll(PageRequest.of(page, size));
        Assertions.assertEquals(0, all.getContent().size());
    }


    @ParameterizedTest
    @CsvSource(value = {"-1,2", "1,0", "-1,0"})
    public void should_get_empty_entity_with_illegal_page_and_size(Integer page, Integer size) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            personRepository.findAll(PageRequest.of(page, size));
        });
    }


    @Test
    public void should_get_least_age_entity_with_sort_of_asc_age() {
        Page<Person> all = personRepository.findAll(PageRequest.of(0, 10,
                Sort.by(Sort.Direction.ASC, "age")));
        Assertions.assertEquals("qxk", all.getContent().stream().findFirst().get().getName());

    }



}
