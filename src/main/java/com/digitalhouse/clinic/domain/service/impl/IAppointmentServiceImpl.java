package com.digitalhouse.clinic.domain.service.impl;

import com.digitalhouse.clinic.domain.dto.AppointmentDTO;
import com.digitalhouse.clinic.domain.dto.mapper.AppointmentDTOMapper;
import com.digitalhouse.clinic.domain.service.IAppointmentService;
import com.digitalhouse.clinic.exception.ResourceNotFoundException;
import com.digitalhouse.clinic.persistence.jparepository.AppointmentJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public AppointmentDTO getById(int id) throws ResourceNotFoundException {

        return repository.findById(id).map(mapper::toDTO)
                .orElseThrow(()-> new ResourceNotFoundException("Appointment not found"));
    }

    @Override
    public AppointmentDTO create(AppointmentDTO appointment) {
        return mapper.toDTO(
                repository.save(
                        mapper.toEntity(appointment)
                )
        );
    }

    @Override
    public AppointmentDTO update(AppointmentDTO appointment) throws ResourceNotFoundException {
        if(repository.existsById(appointment.getId())) throw new ResourceNotFoundException("Appointment not found");
        return mapper.toDTO(repository.save(mapper.toEntity(appointment)));
    }

    @Override
    public void delete(int id) throws ResourceNotFoundException {
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
        repository.deleteById(id);
    }
}
