package com.digitalhouse.clinic.domain.service.impl;

import com.digitalhouse.clinic.domain.dto.DentistDTO;
import com.digitalhouse.clinic.domain.dto.mapper.DentistDTOMapper;
import com.digitalhouse.clinic.domain.service.IDentistService;
import com.digitalhouse.clinic.exception.ResourceNotFoundException;
import com.digitalhouse.clinic.persistence.jparepository.DentistJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IDentistServiceImpl implements IDentistService {
    private final DentistJPARepository repository;
    private final DentistDTOMapper mapper;


    @Autowired
    public IDentistServiceImpl(DentistJPARepository repository, DentistDTOMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<DentistDTO> getAll() {
        return mapper.toDTO(repository.findAll());
    }

    @Override
    public DentistDTO getById(int id) throws ResourceNotFoundException {
        return repository.findById(id).map(mapper::toDTO).orElseThrow(() -> new ResourceNotFoundException("Dentist not found"));
    }

    @Override
    public DentistDTO create(DentistDTO dentist) {
        return mapper.toDTO(
                repository.save(
                        mapper.toEntity(dentist)
                )
        );
    }

    @Override
    public DentistDTO update(DentistDTO dentist) throws ResourceNotFoundException {
        if(repository.existsById(dentist.getId())) throw new ResourceNotFoundException("Dentist not found");
        return mapper.toDTO(repository.save(mapper.toEntity(dentist)));
    }

    @Override
    public void delete(int id) throws ResourceNotFoundException {
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Dentist not found"));
        repository.deleteById(id);
    }
}
