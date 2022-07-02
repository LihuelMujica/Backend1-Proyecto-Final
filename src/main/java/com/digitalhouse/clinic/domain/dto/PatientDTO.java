package com.digitalhouse.clinic.domain.dto;

import com.digitalhouse.clinic.persistence.entity.Address;
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
public class PatientDTO {
    private Integer id;
    private String name;
    private String lastName;
    private String dni;
    private LocalDateTime joinDate;
    private Address address;
}
