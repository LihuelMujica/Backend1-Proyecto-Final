package com.digitalhouse.clinic.domain.service;

import com.digitalhouse.clinic.domain.dto.PatientDTO;
import com.digitalhouse.clinic.exception.ResourceAlreadyExistsException;
import com.digitalhouse.clinic.exception.ResourceNotFoundException;

import java.util.List;

public interface IPatientService {
    List<PatientDTO> getAll();
    PatientDTO getById(int id) throws ResourceNotFoundException;
    PatientDTO create(PatientDTO patient) throws ResourceAlreadyExistsException;
    PatientDTO update(PatientDTO patient) throws ResourceNotFoundException;
    void delete(int id) throws ResourceNotFoundException;
    PatientDTO getByDni(String dni) throws ResourceNotFoundException;
}
