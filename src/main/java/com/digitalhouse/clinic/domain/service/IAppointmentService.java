package com.digitalhouse.clinic.domain.service;

import com.digitalhouse.clinic.domain.dto.AppointmentDTO;
import com.digitalhouse.clinic.exception.BadRequestException;
import com.digitalhouse.clinic.exception.ResourceAlreadyExistsException;
import com.digitalhouse.clinic.exception.ResourceNotFoundException;

import java.util.List;

public interface IAppointmentService {
    List<AppointmentDTO> getAll();
    AppointmentDTO getById(int id) throws ResourceNotFoundException;
    AppointmentDTO create(AppointmentDTO appointment) throws ResourceAlreadyExistsException, BadRequestException;
    AppointmentDTO update(AppointmentDTO appointment) throws ResourceNotFoundException, BadRequestException;
    void delete(int id) throws ResourceNotFoundException;
}
