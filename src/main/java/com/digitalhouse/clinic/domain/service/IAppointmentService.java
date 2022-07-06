package com.digitalhouse.clinic.domain.service;

import com.digitalhouse.clinic.domain.dto.AppointmentDTO;

import java.util.List;
import java.util.Optional;

public interface IAppointmentService {
    List<AppointmentDTO> getAll();
    Optional<AppointmentDTO> getById(int id);
    AppointmentDTO create(AppointmentDTO appointment);
    AppointmentDTO update(AppointmentDTO appointment);
    boolean delete(int id);
}
