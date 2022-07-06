package com.digitalhouse.clinic.domain.service.impl;

import com.digitalhouse.clinic.domain.dto.PatientDTO;
import com.digitalhouse.clinic.domain.dto.mapper.PatientDTOMapper;
import com.digitalhouse.clinic.domain.service.IPatientService;
import com.digitalhouse.clinic.exception.ResourceNotFoundException;
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
    public PatientDTO create(PatientDTO patient) {
        return mapper.toDTO(
                repository.save(
                        mapper.toEntity(patient)
                )
        );
    }

    @Override
    public PatientDTO update(PatientDTO patient) throws ResourceNotFoundException {
        if(repository.existsById(patient.getId())) throw new ResourceNotFoundException("Appointment not found");
        return mapper.toDTO(repository.save(mapper.toEntity(patient)));
    }

    @Override
    public boolean delete(int id) {
        return repository.findById(id).map(patient -> {
            repository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
