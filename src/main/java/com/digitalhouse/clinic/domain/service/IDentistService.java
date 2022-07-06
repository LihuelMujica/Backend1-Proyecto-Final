package com.digitalhouse.clinic.domain.service;

import com.digitalhouse.clinic.domain.dto.DentistDTO;
import com.digitalhouse.clinic.exception.ResourceNotFoundException;

import java.util.List;

public interface IDentistService {
    List<DentistDTO> getAll();
    DentistDTO getById(int id) throws ResourceNotFoundException;
    DentistDTO create(DentistDTO dentist);
    DentistDTO update(DentistDTO dentist) throws ResourceNotFoundException;
    void delete(int id) throws ResourceNotFoundException;
}
