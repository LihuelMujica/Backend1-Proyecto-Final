package com.digitalhouse.clinic.domain.dto.mapper.impl;

import com.digitalhouse.clinic.domain.dto.PatientDTO;
import com.digitalhouse.clinic.domain.dto.mapper.PatientDTOMapper;
import com.digitalhouse.clinic.persistence.entity.Patient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class PatientDTOMapperImpl implements PatientDTOMapper {
    private final ObjectMapper mapper;

    public PatientDTOMapperImpl() {
        this.mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    @Override
    public PatientDTO toDTO(Patient patient) {
        return mapper.convertValue(patient,PatientDTO.class);
    }

    @Override
    public Patient toEntity(PatientDTO patientDTO) {
        return mapper.convertValue(patientDTO,Patient.class);
    }
}
