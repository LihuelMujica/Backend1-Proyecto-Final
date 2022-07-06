package com.digitalhouse.clinic.domain.service.impl;

import com.digitalhouse.clinic.domain.dto.AppointmentDTO;
import com.digitalhouse.clinic.domain.dto.DentistDTO;
import com.digitalhouse.clinic.domain.dto.PatientDTO;
import com.digitalhouse.clinic.domain.service.IAppointmentService;
import com.digitalhouse.clinic.domain.service.IDentistService;
import com.digitalhouse.clinic.domain.service.IPatientService;
import com.digitalhouse.clinic.exception.ResourceNotFoundException;
import com.digitalhouse.clinic.persistence.entity.Address;
import com.digitalhouse.clinic.util.Utils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IAppointmentServiceImplTest {
    private final IAppointmentService service;
    private final IDentistService dentistService;
    private final IPatientService patientService;

    @Autowired
    IAppointmentServiceImplTest(IAppointmentService service, IDentistService dentistService, IPatientService patientService) {
        this.service = service;
        this.dentistService = dentistService;
        this.patientService = patientService;
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    void crudTest(PatientDTO patientDTO,DentistDTO dentistDTO) throws ResourceNotFoundException {
        //Save patient and dentist
        patientDTO = patientService.create(patientDTO);
        dentistDTO = dentistService.create(dentistDTO);
        //Save appointment
        AppointmentDTO appointmentDTO = service.create(
                new AppointmentDTO(null,
                        patientDTO.getId(),
                        dentistDTO.getId(),
                        patientDTO,
                        dentistDTO,
                        LocalDateTime.of(2030,9,6,10,5)
                )
        );

        int id = appointmentDTO.getId();

        //GetById
        AppointmentDTO foundById = service.getById(id);

        assertEquals(appointmentDTO,foundById);

        //Delete
        assertThrows(ResourceNotFoundException.class,() -> service.getById(id));


    }

    private static Stream<Arguments> provideParameters(){
        List<PatientDTO> patientDTOs = Utils.getListOf(
                new PatientDTO(null,"Jorge","Jimenez","123",
                        LocalDateTime.of(2020,4,4,1,20,5),
                        new Address(null,"Calle1","123","Paraná","Entre Ríos")
                ),
                new PatientDTO(null,"Roberto","Gomez","124",
                        LocalDateTime.of(2020,4,4,1,20,5),
                        new Address(null,"Calle2","456","Santa Fe","Santa Fe")
                ),
                new PatientDTO(null,"Jorge","Suarez","14",
                        LocalDateTime.of(2020,4,4,1,20,5),
                        new Address(null,"Calle4","789","Santa Fe","Rosario"))
        );

        List<DentistDTO> dentistDTOS = Utils.getListOf(
                new DentistDTO(null,"Pepe","Jimenez","123"),
                new DentistDTO(null,"Francisco","Marsicano","456"),
                new DentistDTO(null,"Horacio","Rodriguez","789")
        );

        return Stream.of(
                Arguments.of(patientDTOs.get(0),dentistDTOS.get(0)),
                Arguments.of(patientDTOs.get(1),dentistDTOS.get(1)),
                Arguments.of(patientDTOs.get(2),dentistDTOS.get(2))
        );
    }
}