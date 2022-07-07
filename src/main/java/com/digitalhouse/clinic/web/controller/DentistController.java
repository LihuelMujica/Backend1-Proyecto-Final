package com.digitalhouse.clinic.web.controller;

import com.digitalhouse.clinic.domain.dto.DentistDTO;
import com.digitalhouse.clinic.domain.service.IDentistService;
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
@RequestMapping("/dentist")
public class DentistController {
    private final IDentistService service;

    @Autowired
    public DentistController(IDentistService dentistService) {
        this.service = dentistService;
    }

    @GetMapping("/all")
    @ApiOperation("Get a list of all dentists")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<DentistDTO>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Search for a dentist using id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Dentist not found")
    })
    public ResponseEntity<DentistDTO> getById(@ApiParam(value = "The id of the dentist", required = true, example = "1") @PathVariable("id") int id) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.getById(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    @ApiOperation("Save a dentist on the database")
    @ApiResponse(code = 201, message = "CREATED")
    public ResponseEntity<DentistDTO> save(@RequestBody DentistDTO dentist) {
        return new ResponseEntity<>(service.create(dentist), HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{id}")
    @ApiOperation("Delete a dentist using id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Dentist not found")
    })
    public ResponseEntity<?> delete(@PathVariable("id") int id) throws ResourceNotFoundException {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}