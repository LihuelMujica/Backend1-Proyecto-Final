package com.digitalhouse.clinic.domain.service.impl;

import com.digitalhouse.clinic.domain.dto.AppointmentDTO;
import com.digitalhouse.clinic.domain.dto.DentistDTO;
import com.digitalhouse.clinic.domain.dto.PatientDTO;
import com.digitalhouse.clinic.domain.service.IAppointmentService;
import com.digitalhouse.clinic.domain.service.IDentistService;
import com.digitalhouse.clinic.domain.service.IPatientService;
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
    void crudTest(PatientDTO patientDTO,DentistDTO dentistDTO){
        //Save patient and dentist
        patientDTO = patientService.save(patientDTO);
        dentistDTO = dentistService.save(dentistDTO);
        //Save appointment
        AppointmentDTO appointmentDTO = service.save(
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
        Optional<AppointmentDTO> foundById = service.getById(id);

        assertEquals(Optional.of(appointmentDTO),foundById);

        //Delete
        service.delete(id);
        assertEquals(Optional.empty(),service.getById(id));


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