package com.kuan.tddinterview.springdatajpa;

import com.kuan.tddinterview.springdatajpa.inheritance.join.Dog;
import com.kuan.tddinterview.springdatajpa.inheritance.repository.*;
import com.kuan.tddinterview.springdatajpa.inheritance.singletable.BlackBook;
import com.kuan.tddinterview.springdatajpa.inheritance.singletable.RedBook;
import com.kuan.tddinterview.springdatajpa.inheritance.tablepreclass.Female;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;

import javax.annotation.Resource;
import java.util.List;

@DataJpaTest
public class InheritanceTest {

    @Nested
    class SingleTable {
        @Resource
        BookRepository bookRepository;

        @Test
        public void should_save_entity_and_get_correct_size_with_single_table_inheritance() {
            BlackBook black = BlackBook.builder().id(1L).title("黑皮书").blackMark("black-mark").build();
            RedBook red = RedBook.builder().id(2L).title("红皮书").redMark("red-mark").build();
            bookRepository.saveAllAndFlush(List.of(black, red));

            Assertions.assertEquals(2, bookRepository.findAll().size());
        }

        @Test
        public void should_save_entity_with_single_table_inheritance() {
            BlackBook black = BlackBook.builder().id(1L).title("黑皮书").blackMark("black-mark").build();
            RedBook red = RedBook.builder().id(2L).title("红皮书").redMark("red-mark").build();
            bookRepository.saveAllAndFlush(List.of(black, red));

            Assertions.assertEquals(red, bookRepository.findOne(Example.of(RedBook.builder().title("红皮书").build())).get());
        }
    }

    @Nested
    class Joined {
        @Resource
        DogRepository dogRepository;
        @Resource
        AnimalRepository animalRepository;

        @Test
        public void should_save_entity_with_joined_inheritance() {
            Dog dog = Dog.builder().name("ww").play("yy").build();
            dogRepository.saveAndFlush(dog);

            Assertions.assertEquals(1, dogRepository.findAll().size());
            Assertions.assertEquals(1, animalRepository.findAll().size());
        }
    }

    @Nested
    class TablePreClass {
        @Resource
        PeopleRepository peopleRepository;
        @Resource
        FemaleRepository femaleRepository;


        @Test
        public void should_save_entity_and_get_correct_size_with_table_pre_class_inheritance() {
            Female female = Female.builder().name("女性").feature("f1").build();
            femaleRepository.saveAndFlush(female);

            Assertions.assertEquals(1, femaleRepository.findAll().size());
        }


        @Test
        public void should_save_entity_with_table_pre_class_inheritance() {
            peopleRepository.deleteAll();
            Female female = Female.builder().name("女性").feature("f1").build();
            femaleRepository.saveAndFlush(female);

            System.out.println("f1" + ((Female) peopleRepository.findAll().get(0)).getFeature());
        }
    }

}
