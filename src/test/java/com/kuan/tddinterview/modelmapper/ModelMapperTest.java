package com.kuan.tddinterview.modelmapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ModelMapperTest {

    private final String expectedPhone1 = "phone1";
    private final String expectedPhone2 = "phone2";
    final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void should_mapper_object_not_null_with_specific_field() {
        SpecificFieldDto specificFieldDto = mapSpecificField();
        Assertions.assertNotNull(specificFieldDto);
    }

    @Test
    public void should_mapper_object_with_specific_field() {
        SpecificFieldDto specificFieldDto = mapSpecificField();
        Assertions.assertEquals(expectedPhone1, specificFieldDto.getTelephone());
    }

    @Test
    public void should_mapper_object_not_null_with_enum() {
        EnumDto enumDto = mapEnumTypeField();
        Assertions.assertNotNull(enumDto);
    }

    @Test
    public void should_mapper_object_with_enum() {
        EnumDto enumDto = mapEnumTypeField();
        Assertions.assertEquals(Source.Web.getType(), enumDto.getSource());
    }

    @Test
    public void should_mapper_objects_not_null() {
        List<SpecificFieldDto> specificFieldDtos = mapSpecificFieldEntities();
        Assertions.assertEquals(2, specificFieldDtos.size());
    }

    @Test
    public void should_mapper_objects() {
        List<SpecificFieldDto> specificFieldDtos = mapSpecificFieldEntities();
        Set<String> telPhones = specificFieldDtos.stream().map(SpecificFieldDto::getTelephone).collect(Collectors.toSet());

        Assertions.assertTrue(telPhones.contains(expectedPhone1));
        Assertions.assertTrue(telPhones.contains(expectedPhone2));
    }

    private List<SpecificFieldDto> mapSpecificFieldEntities() {
        List<SpecificFieldEntity> specificFieldEntities =
                List.of(new SpecificFieldEntity(expectedPhone1), new SpecificFieldEntity(expectedPhone2));
        return specificFieldEntities.stream().map(this::mapSpecificField).toList();
    }


    private EnumDto mapEnumTypeField() {
        EnumEntity enumEntity = new EnumEntity(Source.Web);
        return modelMapper.typeMap(EnumEntity.class, EnumDto.class)
                .addMappings(mapping -> {
                    mapping.using((Converter<Source, String>) context -> context.getSource().getType())
                            .map(EnumEntity::getSource, EnumDto::setSource);
                }).map(enumEntity);
    }


    private SpecificFieldDto mapSpecificField() {
        return mapSpecificField(new SpecificFieldEntity(expectedPhone1));
    }

    private SpecificFieldDto mapSpecificField(SpecificFieldEntity specificFieldEntity) {
        return modelMapper.typeMap(SpecificFieldEntity.class, SpecificFieldDto.class)
                .addMapping(SpecificFieldEntity::getPhone, SpecificFieldDto::setTelephone)
                .map(specificFieldEntity);
    }


}


