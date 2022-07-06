package com.digitalhouse.clinic.domain.service;

import com.digitalhouse.clinic.domain.dto.AppointmentDTO;
import com.digitalhouse.clinic.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IAppointmentService {
    List<AppointmentDTO> getAll();
    AppointmentDTO getById(int id) throws ResourceNotFoundException;
    AppointmentDTO create(AppointmentDTO appointment);
    AppointmentDTO update(AppointmentDTO appointment);
    boolean delete(int id);
}
