package com.digitalhouse.clinic.domain.dto;

import com.digitalhouse.clinic.persistence.entity.Dentist;
import com.digitalhouse.clinic.persistence.entity.Patient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentDTO {
    private Integer id;
    private Integer patientId;
    private Integer dentistId;
    private PatientDTO patient;
    private DentistDTO dentist;
    private LocalDateTime date;
}
