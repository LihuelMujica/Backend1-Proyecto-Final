package com.digitalhouse.clinic.domain.dto.mapper.impl;

import com.digitalhouse.clinic.domain.dto.AppointmentDTO;
import com.digitalhouse.clinic.domain.dto.mapper.AppointmentDTOMapper;
import com.digitalhouse.clinic.persistence.entity.Appointment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class AppointmentDTOMapperImpl implements AppointmentDTOMapper {
    private final ObjectMapper mapper;

    public AppointmentDTOMapperImpl() {
        this.mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    @Override
    public AppointmentDTO toDTO(Appointment appointment) {
        return  mapper.convertValue(appointment,AppointmentDTO.class);
    }

    @Override
    public Appointment toEntity(AppointmentDTO appointmentDTO) {
        return mapper.convertValue(appointmentDTO,Appointment.class);
    }
}
