package com.kuan.tddinterview.mapper.mapperstruct;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-13T13:37:42+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
public class VideoMapperImpl implements VideoMapper {

    private final DateTimeFormatter dateTimeFormatter_yyyy_MM_dd_0159776256 = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );

    @Override
    public VideoVo toVideoVo(Video video) {
        if ( video == null ) {
            return null;
        }

        VideoVo videoVo = new VideoVo();

        videoVo.setIdentifier( video.getId() );
        if ( video.getPassedAt() != null ) {
            videoVo.setPassedAt( dateTimeFormatter_yyyy_MM_dd_0159776256.format( video.getPassedAt() ) );
        }
        videoVo.setName( video.getName() );
        List<Comment> list = video.getComments();
        if ( list != null ) {
            videoVo.setComments( new ArrayList<Comment>( list ) );
        }
        if ( video.getType() != null ) {
            videoVo.setType( video.getType().name() );
        }

        return videoVo;
    }
}
