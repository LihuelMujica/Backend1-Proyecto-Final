package com.digitalhouse.clinic.persistence.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "dentists")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Dentist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dentist_id")
    private Integer id;

    @Column(name = "name")
    private String name;
    @Column(name = "lastName")
    private String lastname;
    @Column(name = "license")
    private String license;
}
