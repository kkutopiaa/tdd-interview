package com.kuan.tddinterview.mapper.mapperstruct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Video {

    private Long id;

    private String name;

    private List<Comment> comments;

    private Type type;

    private LocalDateTime passedAt;

}


