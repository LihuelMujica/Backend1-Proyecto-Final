package com.digitalhouse.clinic.persistence.jparepository;

import com.digitalhouse.clinic.persistence.entity.Address;
import com.digitalhouse.clinic.persistence.entity.Appointment;
import com.digitalhouse.clinic.persistence.entity.Dentist;
import com.digitalhouse.clinic.persistence.entity.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class AppointmentJPARepositoryTest {
    private final AppointmentJPARepository repository;
    private final PatientJPARepository patientRepository;
    private final DentistJPARepository dentistRepository;

    @Autowired
    AppointmentJPARepositoryTest(AppointmentJPARepository repository, PatientJPARepository patientRepository, DentistJPARepository dentistRepository) {
        this.repository = repository;
        this.patientRepository = patientRepository;
        this.dentistRepository = dentistRepository;
    }

    @Test
    public void curdTest(){

        //Creo patients y los guardo en la base de datos
        List<Patient> patients = new ArrayList<>();
        patients.add(
                new Patient(null,"Jorge","Jimenez","123",
                        LocalDateTime.now(),
                        new Address(null,"Calle1","123","Paraná","Entre Ríos")
                )
        );
        patients.add(
                new Patient(null,"Roberto","Gomez","124",
                        LocalDateTime.now(),
                        new Address(null,"Calle2","456","Santa Fe","Santa Fe")
                )
        );
        patients.add(
                new Patient(null,"Jorge","Suarez","14",
                        LocalDateTime.now(),
                        new Address(null,"Calle4","789","Santa Fe","Rosario")
                )
        );

        patients.forEach(patientRepository::save);

        //Creo dentists y los guardo en la base de datos
        List<Dentist> dentists = new ArrayList<>();
        dentists.add(new Dentist(null,"Pepe","Jimenez","123"));
        dentists.add(new Dentist(null,"Francisco","Marsicano","456"));
        dentists.add(new Dentist(null,"Horacio","Rodriguez","789"));
        dentists.forEach(dentistRepository::save);

        //Creo los appointments:

        List<Appointment> appointments = new ArrayList<>();

        appointments.add(new Appointment(null,patients.get(0).getId(),dentists.get(0).getId(),patients.get(0),dentists.get(0), LocalDateTime.of(2022,9,3,4,5)));
        appointments.add(new Appointment(null,patients.get(1).getId(),dentists.get(1).getId(),patients.get(1),dentists.get(1),LocalDateTime.of(2022,9,3,4,5)));
        appointments.add(new Appointment(null,patients.get(2).getId(),dentists.get(2).getId(),patients.get(2),dentists.get(2),LocalDateTime.of(2022,9,3,4,5)));

        appointments.forEach(repository::save);

        List<Appointment> findByIdAppointments = new ArrayList<>();

        appointments.forEach(a -> repository.findById(a.getId())
                .ifPresent(findByIdAppointments::add));

        assertEquals(findByIdAppointments,appointments);
    }
}