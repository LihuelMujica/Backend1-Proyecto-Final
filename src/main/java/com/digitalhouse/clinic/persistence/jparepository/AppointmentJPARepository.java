package com.digitalhouse.clinic.persistence.jparepository;

import com.digitalhouse.clinic.persistence.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentJPARepository extends JpaRepository<Appointment,Integer> {
}
