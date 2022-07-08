package com.digitalhouse.clinic.domain.service.impl;

import com.digitalhouse.clinic.domain.dto.PatientDTO;
import com.digitalhouse.clinic.domain.dto.mapper.PatientDTOMapper;
import com.digitalhouse.clinic.domain.service.IPatientService;
import com.digitalhouse.clinic.exception.ResourceAlreadyExistsException;
import com.digitalhouse.clinic.exception.ResourceNotFoundException;
import com.digitalhouse.clinic.persistence.entity.Address;
import com.digitalhouse.clinic.persistence.entity.Patient;
import com.digitalhouse.clinic.persistence.jparepository.PatientJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPatientServiceImpl implements IPatientService {
    private final PatientJPARepository repository;
    private final PatientDTOMapper mapper;


    @Autowired
    public IPatientServiceImpl(PatientJPARepository repository, PatientDTOMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<PatientDTO> getAll() {
        return mapper.toDTO(repository.findAll());
    }

    @Override
    public PatientDTO getById(int id) throws ResourceNotFoundException {
        return repository.findById(id).map(mapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
    }

    @Override
    public PatientDTO create(PatientDTO patient) throws ResourceAlreadyExistsException {
        repository.findByDni(patient.getDni()).orElseThrow(() -> new ResourceAlreadyExistsException("A user with this dni already exists"));
        return mapper.toDTO(
                repository.save(
                        mapper.toEntity(patient)
                )
        );
    }

    @Override
    public PatientDTO update(PatientDTO patient) throws ResourceNotFoundException {
        Patient oldPatient = repository.findById(patient.getId()).orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        Patient newPatient = mapper.toEntity(patient);
        if(newPatient.getName()==null) newPatient.setName(oldPatient.getName());
        if(newPatient.getLastName()==null) newPatient.setLastName(oldPatient.getLastName());
        if(newPatient.getDni()==null) newPatient.setDni(oldPatient.getDni());
        if(newPatient.getJoinDate()==null) newPatient.setJoinDate(oldPatient.getJoinDate());
        if(newPatient.getAddress()==null) {
            newPatient.setAddress(oldPatient.getAddress());
        } else {
            Address oldAddress = oldPatient.getAddress();
            Address newAddress = newPatient.getAddress();
            if(newAddress.getId()==null) newAddress.setId(oldAddress.getId());
            if(newAddress.getStreet()==null) newAddress.setStreet(oldAddress.getStreet());
            if(newAddress.getNumber()==null) newAddress.setNumber(oldAddress.getNumber());
            if(newAddress.getLocality()==null) newAddress.setLocality(oldAddress.getLocality());
            if(newAddress.getProvince()==null) newAddress.setProvince(oldAddress.getProvince());
        }
        return mapper.toDTO(repository.save(newPatient));
    }

    @Override
    public void delete(int id) throws ResourceNotFoundException {
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        repository.deleteById(id);
    }
}
