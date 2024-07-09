package com.kuan.tddinterview.modelmapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

public class ModelMapperTest {

    final String expected = "test";
    final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void should_mapper_object_not_null_with_specific_field() {
        SpecificFieldDto specificFieldDto = mapSpecificField();
        Assertions.assertNotNull(specificFieldDto);
    }

    @Test
    public void should_mapper_object_with_specific_field() {
        SpecificFieldDto specificFieldDto = mapSpecificField();
        Assertions.assertEquals(expected, specificFieldDto.getTelephone());
    }

    @Test
    public void should_mapper_object_not_null_with_enum() {
        EnumEntity enumEntity = new EnumEntity(Source.Web);
        EnumDto enumDto = modelMapper.typeMap(EnumEntity.class, EnumDto.class)
                .addMappings(mapping -> {
                    mapping.using((Converter<Source, String>) context -> context.getSource().getType())
                            .map(EnumEntity::getSource, EnumDto::setSource);
                }).map(enumEntity);
        Assertions.assertNotNull(enumDto);
    }


    @Test
    public void should_mapper_object_with_enum() {
        EnumEntity enumEntity = new EnumEntity(Source.Web);
        EnumDto enumDto = modelMapper.typeMap(EnumEntity.class, EnumDto.class)
                .addMappings(mapping -> {
                    mapping.using((Converter<Source, String>) context -> context.getSource().getType())
                            .map(EnumEntity::getSource, EnumDto::setSource);
                }).map(enumEntity);
        Assertions.assertEquals(Source.Web.getType(), enumDto.getSource());
    }


    private SpecificFieldDto mapSpecificField() {
        SpecificFieldEntity specificFieldEntity = new SpecificFieldEntity(expected);
        return modelMapper.typeMap(SpecificFieldEntity.class, SpecificFieldDto.class)
                .addMapping(SpecificFieldEntity::getPhone, SpecificFieldDto::setTelephone)
                .map(specificFieldEntity);
    }


}


