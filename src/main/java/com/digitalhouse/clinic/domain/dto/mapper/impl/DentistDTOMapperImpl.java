package com.digitalhouse.clinic.domain.dto.mapper.impl;

import com.digitalhouse.clinic.domain.dto.DentistDTO;
import com.digitalhouse.clinic.domain.dto.mapper.DentistDTOMapper;
import com.digitalhouse.clinic.persistence.entity.Dentist;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DentistDTOMapperImpl implements DentistDTOMapper {
    private final ObjectMapper mapper;

    public DentistDTOMapperImpl(ObjectMapper mapper) {
        this.mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }


    @Override
    public DentistDTO toDTO(Dentist dentist) {
        return mapper.convertValue(dentist, DentistDTO.class);    }

    @Override
    public List<DentistDTO> toDTO(List<Dentist> c) {
        return c.stream().map(this::toDTO).collect(Collectors.toList());    }

    @Override
    public Dentist toEntity(DentistDTO dentistDTO) {
        return mapper.convertValue(dentistDTO, Dentist.class);
    }

    @Override
    public List<Dentist> toEntity(List<DentistDTO> d) {
        return d.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
