package com.kuan.tddinterview.lombok;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LombokTest {

    final String expectedString = "test";

    @Test
    public void should_assignment_field_by_builder_annotation() {
        TestLombok build = TestLombok.builder().name(expectedString).build();
        Assertions.assertEquals(expectedString, build.getName());
    }

    @Test
    public void should_assignment_field_by_no_args_constructor_annotation() {
        TestLombok testLombok = new TestLombok();
        Assertions.assertNotNull(testLombok);
    }

    @Test
    public void should_assignment_field_by_all_args_constructor_annotation() {
        TestLombok testLombok = new TestLombok(expectedString);
        Assertions.assertEquals(expectedString, testLombok.getName());
    }

    @Test
    public void should_assignment_field_by_setter_getter() {
        TestLombok testLombok = new TestLombok();
        testLombok.setName(expectedString);
        Assertions.assertEquals(expectedString, testLombok.getName());
    }

    @Test
    public void should_display_correct_content_by_to_string_annotation() {
        TestLombok testLombok = new TestLombok(expectedString);
        Assertions.assertEquals("TestLombok(name=test)", testLombok.toString());
    }

}
