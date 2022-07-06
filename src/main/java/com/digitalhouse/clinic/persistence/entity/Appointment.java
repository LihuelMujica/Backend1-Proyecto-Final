package com.digitalhouse.clinic.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Integer id;
    @Column(name = "patient_id")
    private Integer patientId;
    @Column(name = "dentist_id")
    private Integer dentistId;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id",insertable = false,updatable = false)
    @JsonIgnore
    private Patient patient;
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "dentist_id",insertable = false,updatable = false)
    @JsonIgnore
    private Dentist dentist;
    private LocalDateTime date;
}
