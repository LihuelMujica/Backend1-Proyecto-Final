package com.digitalhouse.clinic.web.controller;

import com.digitalhouse.clinic.domain.dto.AppointmentDTO;
import com.digitalhouse.clinic.domain.dto.PatientDTO;
import com.digitalhouse.clinic.domain.service.IAppointmentService;
import com.digitalhouse.clinic.exception.ResourceAlreadyExistsException;
import com.digitalhouse.clinic.exception.ResourceNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    private final IAppointmentService service;

    @Autowired
    public AppointmentController(IAppointmentService dentistService) {
        this.service = dentistService;
    }

    @GetMapping("/all")
    @ApiOperation("Get a list of all appointments")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<AppointmentDTO>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Search for an appointment using id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Appointment not found")
    })
    public ResponseEntity<AppointmentDTO> getById(@ApiParam(value = "The id of the appointment", required = true, example = "1") @PathVariable("id") int id) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.getById(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    @ApiOperation("Save an appointment on the database")
    @ApiResponse(code = 201, message = "CREATED")
    public ResponseEntity<AppointmentDTO> save(@RequestBody AppointmentDTO appointment) throws ResourceAlreadyExistsException {
        return new ResponseEntity<>(service.create(appointment), HttpStatus.CREATED);
    }

    @PatchMapping("/update")
    @ApiOperation("Update an appointment")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "appointment not found")
    })
    public ResponseEntity<AppointmentDTO> update(@RequestBody AppointmentDTO appointmentDTO) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.update(appointmentDTO),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Delete an appointment using id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Appointment not found")
    })
    public ResponseEntity<?> delete(@PathVariable("id") int id) throws ResourceNotFoundException {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}