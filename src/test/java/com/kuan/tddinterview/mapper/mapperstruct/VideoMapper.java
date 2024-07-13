package com.kuan.tddinterview.mapper.mapperstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VideoMapper {

    VideoMapper INSTANCE = Mappers.getMapper(VideoMapper.class);


    @Mappings(value = {
            @Mapping(source = "video.id", target = "identifier"),
            @Mapping(source = "video.passedAt", target = "passedAt", dateFormat = "yyyy-MM-dd")
    })
    VideoVo toVideoVo(Video video);
}
