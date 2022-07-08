package com.digitalhouse.clinic.domain.service.impl;

import com.digitalhouse.clinic.domain.dto.DentistDTO;
import com.digitalhouse.clinic.domain.dto.PatientDTO;
import com.digitalhouse.clinic.exception.ResourceAlreadyExistsException;
import com.digitalhouse.clinic.exception.ResourceNotFoundException;
import com.digitalhouse.clinic.persistence.entity.Address;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IPatientServiceImplTest {
    private final IPatientServiceImpl service;

    @Autowired
    IPatientServiceImplTest(IPatientServiceImpl service) {
        this.service = service;
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    void crudTest(PatientDTO dto) throws ResourceNotFoundException, ResourceAlreadyExistsException {
        //Save and find by id
        dto = service.create(dto);
        int id = dto.getId();
        PatientDTO foundById = service.getById(id);
        assertEquals(dto,foundById);

        dto.setLastName("Fernandez");
        service.update(dto);
        assertEquals(dto,service.getById(dto.getId()));
        foundById.setId(-1);
        assertThrows(ResourceNotFoundException.class,
                () -> service.update(foundById));

        //Delete
        service.delete(id);
        assertThrows(ResourceNotFoundException.class, () -> service.getById(id));
    }

    private static Stream<Arguments> provideParameters(){
        return Stream.of(
                Arguments.of(new PatientDTO(null,"Jorge","Jimenez","4236",
                        LocalDateTime.of(2020,4,4,1,20,5),
                        new Address(null,"Calle1","123","Paraná","Entre Ríos")
                )),
                Arguments.of(new PatientDTO(null,"Roberto","Gomez","7863",
                        LocalDateTime.of(2020,4,4,1,20,5),
                        new Address(null,"Calle2","456","Santa Fe","Santa Fe")
                )),
                Arguments.of(new PatientDTO(null,"Jorge","Suarez","378634",
                        LocalDateTime.of(2020,4,4,1,20,5),
                        new Address(null,"Calle4","789","Santa Fe","Rosario"))
                ));
    }

}