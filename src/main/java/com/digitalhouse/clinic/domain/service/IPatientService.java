package com.digitalhouse.clinic.domain.service;

import com.digitalhouse.clinic.domain.dto.PatientDTO;

import java.util.List;
import java.util.Optional;

public interface IPatientService {
    List<PatientDTO> getAll();
    Optional<PatientDTO> getById(int id);
    PatientDTO save(PatientDTO patient);
    boolean delete(int id);
}
