package com.digitalhouse.clinic.persistence.jparepository;

import com.digitalhouse.clinic.persistence.entity.Dentist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DentistJPARepositoryTest {
    private final DentistJPARepository repository;

    @Autowired
    DentistJPARepositoryTest(DentistJPARepository repository) {
        this.repository = repository;
    }

    @Test
    public void testCRUD(){

        //Creo dentistas
        List<Dentist> dentists = new ArrayList<>();
        dentists.add(new Dentist(null,"Pepe","Jimenez","123"));
        dentists.add(new Dentist(null,"Francisco","Marsicano","456"));
        dentists.add(new Dentist(null,"Horacio","Rodriguez","789"));

        //Los guardo
        dentists.forEach(repository::save);

        //Busco por id y los guardo en una nueva lista

        List<Dentist> dentistsFoundById = new ArrayList<>();
        dentists.forEach(d -> dentistsFoundById.add(repository.findById(d.getId()).get()));

        // Testeo que los dentists coincidan con los dentistsFoundById

        assertEquals(dentists,dentistsFoundById);

        // Ahora probamos buscar por un id que no existe

        assertEquals(repository.findById(-1), Optional.empty());


        //Elimino el primer dentista y testeo que no esté más
        Integer id = dentists.get(0).getId();
        repository.deleteById(id);

        assertEquals(repository.findById(id),Optional.empty());



    }

}