package com.digitalhouse.clinic.domain.dto.mapper.impl;

import com.digitalhouse.clinic.domain.dto.AppointmentDTO;
import com.digitalhouse.clinic.domain.dto.DentistDTO;
import com.digitalhouse.clinic.domain.dto.PatientDTO;
import com.digitalhouse.clinic.domain.dto.mapper.AppointmentDTOMapper;
import com.digitalhouse.clinic.persistence.entity.Appointment;
import com.digitalhouse.clinic.persistence.entity.Dentist;
import com.digitalhouse.clinic.persistence.entity.Patient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

@Component
public class AppointmentDTOMapperImpl implements AppointmentDTOMapper {
    private final ObjectMapper mapper;

    public AppointmentDTOMapperImpl() {
        this.mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    @Override
    public AppointmentDTO toDTO(Appointment appointment) {

        AppointmentDTO appointmentDTO = mapper.convertValue(appointment,AppointmentDTO.class);
        appointmentDTO.setDentist(mapper.convertValue(appointment.getDentist(), DentistDTO.class));
        appointmentDTO.setPatient(mapper.convertValue(appointment.getPatient(), PatientDTO.class));
        return appointmentDTO;
    }

    @Override
    public Appointment toEntity(AppointmentDTO appointmentDTO) {
        Appointment appointment = mapper.convertValue(appointmentDTO,Appointment.class);
        appointment.setDentist(mapper.convertValue(appointmentDTO.getDentist(), Dentist.class));
        appointment.setPatient(mapper.convertValue(appointmentDTO.getPatient(), Patient.class));
        return appointment;
    }
}
