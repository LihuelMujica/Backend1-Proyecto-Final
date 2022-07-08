package com.digitalhouse.clinic.web.controller;

import com.digitalhouse.clinic.domain.dto.PatientDTO;
import com.digitalhouse.clinic.domain.service.IPatientService;
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
@RequestMapping("/patient")
public class PatientController {
    private final IPatientService service;

    @Autowired
    public PatientController(IPatientService service) {
        this.service = service;
    }

    @GetMapping("/all")
    @ApiOperation("Get a list of all patients")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<PatientDTO>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Search for a patient using id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "patient not found")
    })
    public ResponseEntity<PatientDTO> getById(@ApiParam(value = "The id of the patient", required = true, example = "1") @PathVariable("id") int id) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.getById(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    @ApiOperation("Create a patient")
    @ApiResponse(code = 201, message = "CREATED")
    public ResponseEntity<PatientDTO> create(@RequestBody PatientDTO patient) throws ResourceAlreadyExistsException {
        return new ResponseEntity<>(service.create(patient), HttpStatus.CREATED);
    }

    @PatchMapping("/update")
    @ApiOperation("Update a patient")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "patient not found")
    })
    public ResponseEntity<PatientDTO> update(@RequestBody PatientDTO patientDTO) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.update(patientDTO),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Delete a patient using id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Patient not found")
    })
    public ResponseEntity<?> delete(@PathVariable("id") int id) throws ResourceNotFoundException {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
