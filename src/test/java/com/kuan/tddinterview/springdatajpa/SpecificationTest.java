package com.kuan.tddinterview.springdatajpa;

import com.kuan.tddinterview.springdatajpa.specification.SexEnum;
import com.kuan.tddinterview.springdatajpa.specification.model.Person;
import com.kuan.tddinterview.springdatajpa.specification.model.PersonAddress;
import com.kuan.tddinterview.springdatajpa.specification.repository.PersonAddressRepository;
import com.kuan.tddinterview.springdatajpa.specification.repository.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@DataJpaTest
public class SpecificationTest {

    @Resource
    PersonRepository personRepository;

    @Resource
    PersonAddressRepository personAddressRepository;

    Date now = new Date();

    Person person = Person.builder()
            .name("qxk")
            .email("123456@163.com")
            .sex(SexEnum.MAN)
            .age(18)
            .createDate(Instant.now())
            .updateDate(now)
            .build();
    PersonAddress address1 = PersonAddress.builder().address("shanghai").person(person).build();
    PersonAddress address2 = PersonAddress.builder().address("beijing").person(person).build();


    @BeforeEach
    public void setup() {
        personAddressRepository.saveAllAndFlush(List.of(address1, address2));
    }

    @AfterEach
    public void cleanup() {
        personRepository.deleteAll();
    }

    @ParameterizedTest
    @CsvSource(value = {"q", "x", "k"})
    public void should_get_entity_by_fuzzy_query_with_correct_name(String name) {
        List<Person> result = personRepository.findAll((Specification<Person>) (root, query, criteriaBuilder) -> query.where(
                criteriaBuilder.like(root.get("name"), String.join("", List.of("%", name, "%")))
        ).getRestriction());
        Assertions.assertEquals(person, result.get(0));
    }

    @ParameterizedTest
    @CsvSource(value = {"a", "b", "cc"})
    public void should_get_empty_by_fuzzy_query_incorrect_name(String name) {
        List<Person> result = personRepository.findAll((Specification<Person>) (root, query, criteriaBuilder) -> query.where(
                criteriaBuilder.like(root.get("name"), String.join("", List.of("%", name, "%")))
        ).getRestriction());
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void should_get_entity_by_exactly_query_correct_sex() {
        List<Person> result = personRepository.findAll((Specification<Person>) (root, query, criteriaBuilder) -> query.where(
                criteriaBuilder.equal(root.get("sex"), SexEnum.MAN)
        ).getRestriction());
        Assertions.assertEquals(person, result.get(0));
    }

    @Test
    public void should_get_empty_by_exactly_query_incorrect_sex() {
        List<Person> result = personRepository.findAll((Specification<Person>) (root, query, criteriaBuilder) -> query.where(
                criteriaBuilder.equal(root.get("sex"), SexEnum.FEMALE)
        ).getRestriction());
        Assertions.assertTrue(result.isEmpty());
    }


    @ParameterizedTest
    @CsvSource(value = {"0", "10", "18"})
    public void should_get_entity_by_range_query_correct_age(String age) {
        List<Person> result = personRepository.findAll((Specification<Person>) (root, query, criteriaBuilder) -> query.where(
                criteriaBuilder.greaterThanOrEqualTo(root.get("age"), age)
        ).getRestriction());
        Assertions.assertEquals(person, result.get(0));
    }

    @ParameterizedTest
    @CsvSource(value = {"19", "30", "999"})
    public void should_get_empty_by_range_query_incorrect_age(String age) {
        List<Person> result = personRepository.findAll((Specification<Person>) (root, query, criteriaBuilder) -> query.where(
                criteriaBuilder.greaterThanOrEqualTo(root.get("age"), age)
        ).getRestriction());
        Assertions.assertTrue(result.isEmpty());
    }


    @Test
    public void should_get_entity_by_between_query_correct_create_date() {
        List<Person> result = personRepository.findAll((Specification<Person>) (root, query, criteriaBuilder) -> query.where(
                criteriaBuilder.between(root.get("createDate"),
                        Instant.now().plus(-2, ChronoUnit.HOURS), Instant.now().plus(1, ChronoUnit.HOURS))
        ).getRestriction());
        Assertions.assertEquals(person, result.get(0));
    }

    @Test
    public void should_get_empty_by_between_query_incorrect_create_date() {
        List<Person> result = personRepository.findAll((Specification<Person>) (root, query, criteriaBuilder) -> query.where(
                criteriaBuilder.between(root.get("createDate"),
                        Instant.now().plus(-2, ChronoUnit.HOURS), Instant.now().plus(-1, ChronoUnit.HOURS))
        ).getRestriction());
        Assertions.assertTrue(result.isEmpty());
    }


    @Test
    public void should_get_entity_by_join_query_correct_address() {
        List<Person> result = personRepository.findAll((Specification<Person>) (root, query, criteriaBuilder) -> query.where(
                criteriaBuilder.in(root.join("addresses").get("address")).value(List.of("beijing"))
        ).getRestriction());
        Assertions.assertEquals(person, result.get(0));
    }

    @Test
    public void should_get_empty_by_join_query_incorrect_address() {
        List<Person> result = personRepository.findAll((Specification<Person>) (root, query, criteriaBuilder) -> query.where(
                criteriaBuilder.in(root.join("addresses").get("address")).value(List.of("chengdu"))
        ).getRestriction());
        Assertions.assertTrue(result.isEmpty());
    }


}
