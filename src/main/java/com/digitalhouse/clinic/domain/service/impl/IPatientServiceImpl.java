package com.digitalhouse.clinic.domain.service.impl;

import com.digitalhouse.clinic.domain.dto.PatientDTO;
import com.digitalhouse.clinic.domain.dto.mapper.PatientDTOMapper;
import com.digitalhouse.clinic.domain.service.IPatientService;
import com.digitalhouse.clinic.exception.ResourceAlreadyExistsException;
import com.digitalhouse.clinic.exception.ResourceNotFoundException;
import com.digitalhouse.clinic.persistence.entity.Address;
import com.digitalhouse.clinic.persistence.entity.Patient;
import com.digitalhouse.clinic.persistence.jparepository.PatientJPARepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPatientServiceImpl implements IPatientService {
    private final PatientJPARepository repository;
    private final PatientDTOMapper mapper;
    private static final Logger LOGGER = Logger.getLogger(IPatientServiceImpl.class);


    @Autowired
    public IPatientServiceImpl(PatientJPARepository repository, PatientDTOMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<PatientDTO> getAll() {
        LOGGER.info("Getting all patients (service-getall)");

        return mapper.toDTO(repository.findAll());
    }

    @Override
    public PatientDTO getById(int id) throws ResourceNotFoundException {
        LOGGER.info("Getting a patient (service-getById)");
        LOGGER.info("Input: id="+id);
        return repository.findById(id).map(mapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
    }

    @Override
    public PatientDTO create(PatientDTO patient) throws ResourceAlreadyExistsException {
        LOGGER.info("Saving a patient (service-create)");
        LOGGER.info("input:  "+patient);
        if(repository.findByDni(patient.getDni()).isPresent()) throw  new ResourceAlreadyExistsException("A user with this dni already exists");
        patient.setId(null);
        return mapper.toDTO(
                repository.save(
                        mapper.toEntity(patient)
                )
        );
    }

    @Override
    public PatientDTO update(PatientDTO patient) throws ResourceNotFoundException {
        LOGGER.info("Updating a patiient (service-update)");
        LOGGER.info("input:  "+patient);
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
        LOGGER.info("Deleting a patient (service-delete)");
        LOGGER.info("input:  id= "+ id);
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        repository.deleteById(id);
    }

    @Override
    public PatientDTO getByDni(String dni) throws ResourceNotFoundException {
        LOGGER.info("Getting a poatient (service-getByDni)");
        LOGGER.info("input:  dni= "+ dni);
        return mapper.toDTO(repository
                .findByDni(dni).orElseThrow(() -> new ResourceNotFoundException("Patient not found")));
    }
}
