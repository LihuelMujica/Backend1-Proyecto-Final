package com.digitalhouse.clinic.domain.service;

import com.digitalhouse.clinic.domain.dto.AppointmentDTO;
import com.digitalhouse.clinic.exception.ResourceNotFoundException;

import java.util.List;

public interface IAppointmentService {
    List<AppointmentDTO> getAll();
    AppointmentDTO getById(int id) throws ResourceNotFoundException;
    AppointmentDTO create(AppointmentDTO appointment);
    AppointmentDTO update(AppointmentDTO appointment) throws ResourceNotFoundException;
    void delete(int id) throws ResourceNotFoundException;
}
