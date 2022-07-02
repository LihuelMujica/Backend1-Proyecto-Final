package com.digitalhouse.clinic.domain.dto.mapper;

import com.digitalhouse.clinic.domain.dto.DentistDTO;
import com.digitalhouse.clinic.domain.dto.mapper.impl.DentistDTOMapperImpl;
import com.digitalhouse.clinic.persistence.entity.Dentist;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


class IDTOMapperTest {
    @ParameterizedTest
    @MethodSource("provideParameters")
    void toDTOtest(IDTOMapper<Object,Object> mapper, List<Object> entity, List<Object> dto){
        List<Object> entityToDTO = entity.stream().map(mapper::toDTO).collect(Collectors.toList());
        assertEquals(dto,entityToDTO);
        assertEquals(dto,mapper.toDTO(entity));

    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    void toEntityTest(IDTOMapper<Object,Object> mapper, List<Object> entity, List<Object> dto){
        List<Object> dtoToEntity = dto.stream().map(mapper::toEntity).collect(Collectors.toList());
        assertEquals(entity,dtoToEntity);
        assertEquals(entity,mapper.toEntity(dto));

    }

    private static Stream<Arguments> provideParameters() {
        List<Dentist> dentists = new ArrayList<>();
        dentists.add(new Dentist(1,"Pepe","Jimenez","123"));
        dentists.add(new Dentist(2,"Francisco","Marsicano","456"));
        dentists.add(new Dentist(3,"Horacio","Rodriguez","789"));
        List<DentistDTO> detnistsDTOs = new ArrayList<>();
        detnistsDTOs.add(new DentistDTO(1,"Pepe","Jimenez","123"));
        detnistsDTOs.add(new DentistDTO(2,"Francisco","Marsicano","456"));
        detnistsDTOs.add(new DentistDTO(3,"Horacio","Rodriguez","789"));


        return Stream.of(
                Arguments.of(new DentistDTOMapperImpl(), dentists,detnistsDTOs)
        );
    }
}