package com.kuan.tddinterview.springdatajpa;

import com.kuan.tddinterview.springdatajpa.cascade.many2many.CourseEntity;
import com.kuan.tddinterview.springdatajpa.cascade.many2many.TeacherEntity;
import com.kuan.tddinterview.springdatajpa.cascade.one2manyandmany2one.ClassEntity;
import com.kuan.tddinterview.springdatajpa.cascade.one2manyandmany2one.StudentEntity;
import com.kuan.tddinterview.springdatajpa.cascade.one2one.OneToOneEntity;
import com.kuan.tddinterview.springdatajpa.cascade.one2one.OneToOneExtEntity;
import com.kuan.tddinterview.springdatajpa.cascade.repository.*;

import javax.annotation.Resource;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;

import java.util.List;

@DataJpaTest
public class CascadeTypeTest {

    @Nested
    class OneToOne {
        @Resource
        OneToOneEntityRepository oneToOneEntityRepository;

        @Resource
        OneToOneExtEntityRepository oneToOneExtEntityRepository;

        @Test
        public void should_save_entity_with_one_to_one_cascade_type() {
            initOneToOneRelationship();

            Assertions.assertEquals("test-ext", oneToOneExtEntityRepository.findAll().get(0).getDescription());
            Assertions.assertEquals("test-one-to-one", oneToOneEntityRepository.findAll().get(0).getName());
        }

        @Test
        public void should_delete_entity_with_one_to_one_cascade_type() {
            OneToOneEntity main = initOneToOneRelationship();
            oneToOneEntityRepository.delete(main);

            Assertions.assertEquals(0, oneToOneEntityRepository.findAll().size());
            Assertions.assertEquals(0, oneToOneExtEntityRepository.findAll().size());
        }

        private OneToOneEntity initOneToOneRelationship() {
            OneToOneExtEntity sub = OneToOneExtEntity.builder().description("test-ext").build();
            OneToOneEntity main = OneToOneEntity.builder().name("test-one-to-one").oneToOneExtEntity(sub).build();
            oneToOneEntityRepository.saveAndFlush(main);
            return main;
        }
    }

    @Resource
    ClassEntityRepository classEntityRepository;

    @Resource
    StudentEntityRepository studentEntityRepository;

    @Nested
    class ManyToOne {
        private final ClassEntity one = ClassEntity.builder().name("one").build();
        private final StudentEntity many2 = StudentEntity.builder().cloth("many-2").classEntity(one).build();
        private final StudentEntity many1 = StudentEntity.builder().cloth("many-1").classEntity(one).build();

        @BeforeEach
        public void init() {
            studentEntityRepository.saveAllAndFlush(List.of(many1, many2));
        }

        @AfterEach
        public void clean() {
            studentEntityRepository.deleteAll();
            classEntityRepository.deleteAll();
        }

        @Test
        public void should_save_entity_and_get_correct_size_with_many_to_one_cascade_type() {
            Assertions.assertEquals(2, studentEntityRepository.findAll().size());
            Assertions.assertEquals(1, classEntityRepository.findAll().size());
        }

        @Test
        public void should_save_entity_and_get_correct_entity_with_many_to_one_cascade_type() {
            Assertions.assertEquals(one,
                    classEntityRepository.findOne(Example.of(ClassEntity.builder().name("one").build())).get());
            Assertions.assertEquals(many1,
                    studentEntityRepository.findOne(Example.of(StudentEntity.builder().cloth("many-1").build())).get());
        }

        @Test
        public void should_delete_one_entity_with_many_to_one_cascade_type() {
            studentEntityRepository.delete(many1);

            Assertions.assertEquals(1, studentEntityRepository.findAll().size());
            Assertions.assertEquals(1, classEntityRepository.findAll().size());
        }

        @Test
        public void should_delete_all_entity_with_many_to_one_cascade_type() {
            studentEntityRepository.deleteAll();

            Assertions.assertEquals(0, studentEntityRepository.findAll().size());
            Assertions.assertEquals(0, classEntityRepository.findAll().size());
        }

    }

    @Nested
    class OneToMany {

        private final StudentEntity many1 = StudentEntity.builder().cloth("many-1").build();
        private final StudentEntity many2 = StudentEntity.builder().cloth("many-2").build();
        private final ClassEntity one = ClassEntity.builder().name("one").students(List.of(many1, many2)).build();


        @BeforeEach
        public void init() {
            classEntityRepository.saveAndFlush(one);
        }

        @AfterEach
        public void clean() {
            studentEntityRepository.deleteAll();
            classEntityRepository.deleteAll();
        }

        @Test
        public void should_save_entity_and_get_correct_size_with_one_to_many_cascade_type() {
            Assertions.assertEquals(2, studentEntityRepository.findAll().size());
            Assertions.assertEquals(1, classEntityRepository.findAll().size());
        }


        @Test
        public void should_save_entity_and_get_correct_entity_with_one_to_many_cascade_type() {
            Assertions.assertEquals(one,
                    classEntityRepository.findOne(Example.of(ClassEntity.builder().name("one").build())).get());
            Assertions.assertEquals(many1,
                    studentEntityRepository.findOne(Example.of(StudentEntity.builder().cloth("many-1").build())).get());
        }


        @Test
        public void should_delete_all_entity_with_many_to_one_cascade_type() {
            classEntityRepository.deleteAll();

            Assertions.assertEquals(0, studentEntityRepository.findAll().size());
            Assertions.assertEquals(0, classEntityRepository.findAll().size());
        }
    }


    @Nested
    class ManyToMany {
        @Resource
        TeacherEntityRepository teacherEntityRepository;
        @Resource
        CourseEntityRepository courseEntityRepository;

        private final CourseEntity c1 = CourseEntity.builder().description("c1").build();
        private final List<CourseEntity> courses = List.of(c1, CourseEntity.builder().description("c2").build());
        private final TeacherEntity t2 = TeacherEntity.builder().name("t2").courses(courses).build();
        private final TeacherEntity t1 = TeacherEntity.builder().name("t1").courses(courses).build();

        @BeforeEach
        public void init() {
            teacherEntityRepository.saveAllAndFlush(List.of(t1, t2));
        }

        @AfterEach
        public void clean() {
            teacherEntityRepository.deleteAll();
            courseEntityRepository.deleteAll();
        }

        @Test
        public void should_save_entity_and_get_correct_size_with_one_to_many_cascade_type() {
            Assertions.assertEquals(2, teacherEntityRepository.findAll().size());
            Assertions.assertEquals(2, courseEntityRepository.findAll().size());
        }

        @Test
        public void should_save_entity_and_get_correct_entity_with_one_to_many_cascade_type() {
            Assertions.assertEquals(t1,
                    teacherEntityRepository.findOne(Example.of(TeacherEntity.builder().name("t1").build())).get());
            Assertions.assertEquals(c1,
                    courseEntityRepository.findOne(Example.of(CourseEntity.builder().description("c1").build())).get());
        }

        @Test
        public void should_delete_one_entity_with_many_to_many_cascade_type() {
            teacherEntityRepository.delete(t1);

            Assertions.assertEquals(1, teacherEntityRepository.findAll().size());
            Assertions.assertEquals(2, courseEntityRepository.findAll().size());
        }


        @Test
        public void should_delete_all_entity_with_many_to_many_cascade_type() {
            teacherEntityRepository.deleteAll();

            Assertions.assertEquals(0, teacherEntityRepository.findAll().size());
            Assertions.assertEquals(0, courseEntityRepository.findAll().size());
        }

    }

}
