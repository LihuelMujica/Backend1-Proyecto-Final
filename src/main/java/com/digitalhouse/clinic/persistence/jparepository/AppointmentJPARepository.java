package com.digitalhouse.clinic.persistence.jparepository;

import com.digitalhouse.clinic.persistence.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AppointmentJPARepository extends JpaRepository<Appointment,Integer> {
    Optional<Appointment> findByDentistIdAndDate(Integer dentistId, LocalDateTime date);
    Optional<Appointment> findByDentistId(Integer dentistId);
}
