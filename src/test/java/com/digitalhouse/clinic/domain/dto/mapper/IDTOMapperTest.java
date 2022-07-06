package com.digitalhouse.clinic.domain.dto.mapper;

import com.digitalhouse.clinic.domain.dto.AppointmentDTO;
import com.digitalhouse.clinic.domain.dto.DentistDTO;
import com.digitalhouse.clinic.domain.dto.PatientDTO;
import com.digitalhouse.clinic.domain.dto.mapper.impl.AppointmentDTOMapperImpl;
import com.digitalhouse.clinic.domain.dto.mapper.impl.DentistDTOMapperImpl;
import com.digitalhouse.clinic.domain.dto.mapper.impl.PatientDTOMapperImpl;
import com.digitalhouse.clinic.persistence.entity.Address;
import com.digitalhouse.clinic.persistence.entity.Appointment;
import com.digitalhouse.clinic.persistence.entity.Dentist;
import com.digitalhouse.clinic.persistence.entity.Patient;
import com.digitalhouse.clinic.util.Utils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
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

        //Dentist

        List<Dentist> dentists = Utils.getListOf(
                new Dentist(1,"Pepe","Jimenez","123"),
                new Dentist(2,"Francisco","Marsicano","456"),
                new Dentist(3,"Horacio","Rodriguez","789")
        );
        List<DentistDTO> dentistDTOS = Utils.getListOf(
                new DentistDTO(1,"Pepe","Jimenez","123"),
                new DentistDTO(2,"Francisco","Marsicano","456"),
                new DentistDTO(3,"Horacio","Rodriguez","789")
        );

        //Patient

        List<Patient> patients = Utils.getListOf(
                new Patient(1,"Jorge","Jimenez","123",
                        LocalDateTime.of(2020,4,4,1,20,5),
                        new Address(1,"Calle1","123","Paraná","Entre Ríos")
                ),
                new Patient(2,"Roberto","Gomez","124",
                        LocalDateTime.of(2020,4,4,1,20,5),
                        new Address(2,"Calle2","456","Santa Fe","Santa Fe")
                ),
                new Patient(3,"Jorge","Suarez","14",
                        LocalDateTime.of(2020,4,4,1,20,5),
                        new Address(3,"Calle4","789","Santa Fe","Rosario")
                )
        );

        List<PatientDTO> patientDTOs = Utils.getListOf(
                new PatientDTO(1,"Jorge","Jimenez","123",
                        LocalDateTime.of(2020,4,4,1,20,5),
                        new Address(1,"Calle1","123","Paraná","Entre Ríos")
                ),
                new PatientDTO(2,"Roberto","Gomez","124",
                        LocalDateTime.of(2020,4,4,1,20,5),
                        new Address(2,"Calle2","456","Santa Fe","Santa Fe")
                ),
                new PatientDTO(3,"Jorge","Suarez","14",
                        LocalDateTime.of(2020,4,4,1,20,5),
                        new Address(3,"Calle4","789","Santa Fe","Rosario"))
        );

        List<Appointment> appointments = Utils.getListOf(
                new Appointment(
                        1,
                        patients.get(0).getId(),
                        dentists.get(0).getId(),
                        patients.get(0),dentists.get(0),
                        LocalDateTime.of(2022,9,3,4,5)),
                new Appointment(2,
                        patients.get(1).getId(),
                        dentists.get(1).getId(),patients.get(1),
                        dentists.get(1),LocalDateTime.of(2022,9,3,4,5)),
                new Appointment(3,patients.get(2).getId(),
                        dentists.get(2).getId(),
                        patients.get(2),
                        dentists.get(2),
                        LocalDateTime.of(2022,9,3,4,5))
        );
        List<AppointmentDTO> appointmentDTOS = Utils.getListOf(
                new AppointmentDTO(
                        1,
                        patients.get(0).getId(),
                        dentists.get(0).getId(),
                        patientDTOs.get(0),dentistDTOS.get(0),
                        LocalDateTime.of(2022,9,3,4,5)),
                new AppointmentDTO(2,
                        patients.get(1).getId(),
                        dentists.get(1).getId(),patientDTOs.get(1),
                        dentistDTOS.get(1),LocalDateTime.of(2022,9,3,4,5)),
                new AppointmentDTO(3,patients.get(2).getId(),
                        dentists.get(2).getId(),
                        patientDTOs.get(2),
                        dentistDTOS.get(2),
                        LocalDateTime.of(2022,9,3,4,5))
        );



        return Stream.of(
                Arguments.of(new DentistDTOMapperImpl(), dentists,dentistDTOS),
                Arguments.of(new PatientDTOMapperImpl(), patients, patientDTOs),
                Arguments.of(new AppointmentDTOMapperImpl(),appointments,appointmentDTOS)
        );
    }
}