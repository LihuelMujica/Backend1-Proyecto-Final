package com.digitalhouse.clinic.domain.service.impl;

import com.digitalhouse.clinic.domain.dto.DentistDTO;
import com.digitalhouse.clinic.domain.service.IDentistService;
import com.digitalhouse.clinic.exception.ResourceNotFoundException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class IDentistServiceImplTest {
    private final IDentistService service;

    @Autowired
    IDentistServiceImplTest(IDentistService service) {
        this.service = service;
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    void crudTest(DentistDTO dto) throws ResourceNotFoundException {
        //Save and find by id
        dto = service.create(dto);
        int id = dto.getId();
        DentistDTO foundById = service.getById(id);
        assertEquals(dto,foundById);

        //Delete
        assertThrows(ResourceNotFoundException.class,() -> service.getById(id));
    }

    private static Stream<Arguments> provideParameters(){
        return Stream.of(
                Arguments.of(new DentistDTO(null,"Pepe","Jimenez","123")),
                Arguments.of(new DentistDTO(null,"Francisco","Marsicano","456")),
                Arguments.of(new DentistDTO(null,"Horacio","Rodriguez","789"))
        );
    }
}