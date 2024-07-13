package com.kuan.tddinterview.mapper.mapperstruct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VideoVo {

    private Long identifier;

    private String name;

    private List<Comment> comments;

    private String type;

    private String passedAt;

}
