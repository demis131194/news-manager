package com.epam.lab.exception;

import com.epam.lab.exeption.ServiceException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<RespondExceptionMessage> handleDataAccessException(DataAccessException e) {
        return new ResponseEntity<>(new RespondExceptionMessage(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<RespondExceptionMessage> handleServiceException(ServiceException e) {
        return new ResponseEntity<>(new RespondExceptionMessage(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }
}
