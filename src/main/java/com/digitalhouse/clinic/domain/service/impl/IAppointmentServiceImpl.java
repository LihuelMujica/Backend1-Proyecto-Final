package com.digitalhouse.clinic.domain.service.impl;

import com.digitalhouse.clinic.domain.dto.AppointmentDTO;
import com.digitalhouse.clinic.domain.dto.mapper.AppointmentDTOMapper;
import com.digitalhouse.clinic.domain.service.IAppointmentService;
import com.digitalhouse.clinic.exception.BadRequestException;
import com.digitalhouse.clinic.exception.ResourceAlreadyExistsException;
import com.digitalhouse.clinic.exception.ResourceNotFoundException;
import com.digitalhouse.clinic.persistence.entity.Appointment;
import com.digitalhouse.clinic.persistence.jparepository.AppointmentJPARepository;
import com.digitalhouse.clinic.persistence.jparepository.DentistJPARepository;
import com.digitalhouse.clinic.persistence.jparepository.PatientJPARepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IAppointmentServiceImpl implements IAppointmentService {
    private final AppointmentJPARepository repository;
    private final DentistJPARepository dentistRepository;
    private final PatientJPARepository patientRepository;
    private final AppointmentDTOMapper mapper;
    private static final Logger LOGGER = Logger.getLogger(IAppointmentServiceImpl.class);

    @Autowired
    public IAppointmentServiceImpl(AppointmentJPARepository repository, DentistJPARepository dentistRepository, PatientJPARepository patientRepository, AppointmentDTOMapper mapper) {
        this.repository = repository;
        this.dentistRepository = dentistRepository;
        this.patientRepository = patientRepository;
        this.mapper = mapper;
    }

    @Override
    public List<AppointmentDTO> getAll() {
        LOGGER.info("Getting all appointments (service-getall)");
        return mapper.toDTO(repository.findAll());
    }

    @Override
    public AppointmentDTO getById(int id) throws ResourceNotFoundException {
        LOGGER.info("Getting an appointment (service-getById)");
        LOGGER.info("Input: id="+id);
        return repository.findById(id).map(mapper::toDTO)
                .orElseThrow(()-> new ResourceNotFoundException("Appointment not found"));
    }

    @Override
    public AppointmentDTO create(AppointmentDTO appointment) throws ResourceAlreadyExistsException, BadRequestException {
        LOGGER.info("Saving an appointment (service-create)");
        LOGGER.info("input:  "+ appointment);
        if(!dentistRepository.existsById(appointment.getDentistId()) || !patientRepository.existsById(appointment.getPatientId()))
            throw new BadRequestException("The patient or the dentist doesn't exists");
        if(repository.findByDentistIdAndDate(appointment.getDentistId(),appointment.getDate()).isPresent()) throw  new ResourceAlreadyExistsException("This appointment is already assigned");
        appointment.setId(null);
        return mapper.toDTO(
                repository.save(
                        mapper.toEntity(appointment)
                )
        );
    }

    @Override
    public AppointmentDTO update(AppointmentDTO appointment) throws ResourceNotFoundException, BadRequestException {
        LOGGER.info("Updating an appointment (service-update)");
        LOGGER.info("input:  "+ appointment);
        if(dentistRepository.existsById(appointment.getDentistId()) && patientRepository.existsById(appointment.getPatientId()))
            throw new BadRequestException("The patient or the dentist doesn't exists");
        Appointment oldAppointment = repository.findById(appointment.getId()).orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
        Appointment newAppointment = mapper.toEntity(appointment);
        if(newAppointment.getPatientId()==null) newAppointment.setPatientId(oldAppointment.getPatientId());
        if(newAppointment.getDentistId()==null) newAppointment.setDentistId(oldAppointment.getDentistId());
        if(newAppointment.getDate()==null) newAppointment.setDate(oldAppointment.getDate());
        return mapper.toDTO(repository.save(newAppointment));
    }

    @Override
    public void delete(int id) throws ResourceNotFoundException {
        LOGGER.info("Deleting an appointment (service-delete)");
        LOGGER.info("input:  id= "+ id);
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
        repository.deleteById(id);
    }
}
