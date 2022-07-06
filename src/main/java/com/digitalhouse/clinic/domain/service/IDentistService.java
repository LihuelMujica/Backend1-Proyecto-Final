package com.digitalhouse.clinic.domain.service;

import com.digitalhouse.clinic.domain.dto.DentistDTO;
import com.digitalhouse.clinic.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IDentistService {
    List<DentistDTO> getAll();
    DentistDTO getById(int id) throws ResourceNotFoundException;
    DentistDTO create(DentistDTO dentist);
    DentistDTO update(DentistDTO dentist) throws ResourceNotFoundException;
    boolean delete(int id);
}
