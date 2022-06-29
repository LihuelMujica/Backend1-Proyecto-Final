package com.digitalhouse.clinic.persistence.jparepository;

import com.digitalhouse.clinic.persistence.entity.Address;
import com.digitalhouse.clinic.persistence.entity.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PatientJPARepositoryTest {
    private final PatientJPARepository repository;

    @Autowired
    PatientJPARepositoryTest(PatientJPARepository repository) {
        this.repository = repository;
    }

    @Test
    public void testCrud(){
        //Creo patients

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

        //Los guardo
        patients.forEach(repository::save);

        //Busco cada uno por id y los guardo en una nueva lista

        List<Patient> patientsFoundbyId = new ArrayList<>();
        patients.forEach(p -> patientsFoundbyId.add(repository.findById(p.getId()).get()));

        // Testeo que los patients coincidan con los patientsFoundById

        assertEquals(patients,patientsFoundbyId);

        // Ahora pruebo buscar por un id que no existe

        assertEquals(repository.findById(-1), Optional.empty());

        //Elimino el primer paciente y testeo que no esté más

        Integer id = patients.get(0).getId();
        repository.deleteById(id);

        assertEquals(repository.findById(id),Optional.empty());


    }
}