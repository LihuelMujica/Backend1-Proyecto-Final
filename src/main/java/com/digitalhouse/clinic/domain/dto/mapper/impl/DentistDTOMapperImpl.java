package com.digitalhouse.clinic.domain.dto.mapper.impl;

import com.digitalhouse.clinic.domain.dto.DentistDTO;
import com.digitalhouse.clinic.domain.dto.mapper.DentistDTOMapper;
import com.digitalhouse.clinic.persistence.entity.Dentist;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

@Component
public class DentistDTOMapperImpl implements DentistDTOMapper {
    private final ObjectMapper mapper;

    public DentistDTOMapperImpl() {
        this.mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }


    @Override
    public DentistDTO toDTO(Dentist dentist) {
        return mapper.convertValue(dentist, DentistDTO.class);
    }


    @Override
    public Dentist toEntity(DentistDTO dentistDTO) {
        return mapper.convertValue(dentistDTO, Dentist.class);
    }


}
