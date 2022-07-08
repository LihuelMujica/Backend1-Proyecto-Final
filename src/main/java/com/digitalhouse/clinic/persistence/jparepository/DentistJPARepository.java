package com.digitalhouse.clinic.persistence.jparepository;

import com.digitalhouse.clinic.persistence.entity.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DentistJPARepository extends JpaRepository<Dentist,Integer> {
    Optional<Dentist> findByLicense(String license);
}
