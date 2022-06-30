package com.digitalhouse.clinic.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DentistDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String license;

}
