package com.digitalhouse.clinic.exception;

import com.digitalhouse.clinic.domain.service.impl.IPatientServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> resourceNotFoundException(ResourceNotFoundException ex) {
        LOGGER.info(ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler({ResourceAlreadyExistsException.class})
    public ResponseEntity<String> resourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
        LOGGER.info(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
