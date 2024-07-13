package com.kuan.tddinterview.mapper.mapperstruct;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class MapperStructTest {
    private final long expectedId = 1L;
    private final String expectedName = "test";
    private final List<Comment> expectedComments = List.of(new Comment("comment-1"),
            new Comment("comment-2"));
    private final Type expectedType = Type.VIP;
    VideoVo videoVo;

    @BeforeEach
    public void setup() {
        Video video = new Video(expectedId, expectedName, expectedComments, expectedType,
                LocalDateTime.of(2024, 7, 13, 12, 12));
        videoVo = VideoMapper.INSTANCE.toVideoVo(video);
    }

    @Test
    public void should_get_vo_by_map_struct() {
        Assertions.assertEquals(VideoVo.class, videoVo.getClass());
    }

    @Test
    public void should_get_vo_with_not_same_property_name_by_map_struct() {
        Assertions.assertEquals(expectedId, videoVo.getIdentifier());
    }

    @Test
    public void should_get_vo_with_same_property_name_by_map_struct() {
        Assertions.assertEquals(expectedName, videoVo.getName());
    }

    @Test
    public void should_get_vo_with_collection_by_map_struct() {
        Assertions.assertEquals(expectedComments, videoVo.getComments());
    }

    @Test
    public void should_get_vo_with_enum_by_map_struct() {
        Assertions.assertEquals(expectedType.name(), videoVo.getType());
    }

    @Test
    public void should_get_vo_with_date_by_map_struct() {
        Assertions.assertEquals("2024-07-13", videoVo.getPassedAt());
    }


}
