package com.digitalhouse.clinic.domain.service.impl;

import com.digitalhouse.clinic.domain.dto.AppointmentDTO;
import com.digitalhouse.clinic.domain.dto.mapper.AppointmentDTOMapper;
import com.digitalhouse.clinic.domain.service.IAppointmentService;
import com.digitalhouse.clinic.persistence.jparepository.AppointmentJPARepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class IAppointmentServiceImpl implements IAppointmentService {
    private final AppointmentJPARepository repository;
    private final AppointmentDTOMapper mapper;

    @Autowired
    public IAppointmentServiceImpl(AppointmentJPARepository repository, AppointmentDTOMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<AppointmentDTO> getAll() {
        return mapper.toDTO(repository.findAll());
    }

    @Override
    public Optional<AppointmentDTO> getById(int id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    @Override
    public AppointmentDTO save(AppointmentDTO appointment) {
        return mapper.toDTO(
                repository.save(
                        mapper.toEntity(appointment)
                )
        );
    }

    @Override
    public boolean delete(int id) {
        return repository.findById(id).map(d -> {
            repository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
