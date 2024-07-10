package com.kuan.tddinterview.springboottest;

import java.util.List;

public class TestObject<T> {

    private List<T> elements;

    public TestObject() {
    }

    public TestObject(List<T> elements) {
        this.elements = elements;
    }

    public List<T> getElements() {
        return elements;
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }
}
