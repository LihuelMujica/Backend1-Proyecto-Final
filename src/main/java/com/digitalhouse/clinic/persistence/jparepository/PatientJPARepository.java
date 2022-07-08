package com.digitalhouse.clinic.persistence.jparepository;

import com.digitalhouse.clinic.persistence.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientJPARepository extends JpaRepository<Patient,Integer> {
    Optional<Patient> findByDni(String dni);
}
