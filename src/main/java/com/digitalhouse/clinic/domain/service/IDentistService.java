package com.digitalhouse.clinic.domain.service;

import com.digitalhouse.clinic.domain.dto.DentistDTO;

import java.util.List;
import java.util.Optional;

public interface IDentistService {
    List<DentistDTO> getAll();
    Optional<DentistDTO> getById(int id);
    DentistDTO save(DentistDTO dentist);
    boolean delete(int id);
}
