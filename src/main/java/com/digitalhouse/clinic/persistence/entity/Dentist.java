package com.digitalhouse.clinic.persistence.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "dentists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dentist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dentist_id")
    private Integer id;

    @Column(name = "name")
    private String nombre;
    @Column(name = "lastName")
    private String apellido;
    @Column(name = "license")
    private String license;
}
