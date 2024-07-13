package com.kuan.tddinterview.mapper.modelmapper;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Source {

    Mobile("1", "移动端"),
    Web("2", "网页端"),
    Other("3", "其他");

    private String type;
    private String description;

    Source(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    private static Map<String, Source> map = Stream.of(Source.values())
            .collect(Collectors.toMap(Source::getType, Function.identity(), (v1, v2) -> v1));

    public static Source getInstance(String type) {
        return map.get(type);
    }

}
