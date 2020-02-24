package com.epam.lab.exception;

import com.epam.lab.exeption.ServiceException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.PersistenceException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<RespondExceptionMessage> handleDataAccessException(DataAccessException e) {
        Throwable rootException = getRootThrowable(e);
        return new ResponseEntity<>(new RespondExceptionMessage(e.getClass().getSimpleName() + " " + e.getLocalizedMessage(),
                rootException.getClass().getSimpleName() + " " + rootException.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<RespondExceptionMessage> handleServiceException(ServiceException e) {
        Throwable rootException = getRootThrowable(e);
        return new ResponseEntity<>(new RespondExceptionMessage(e.getClass().getSimpleName() + " " + e.getLocalizedMessage(),
                rootException.getClass().getSimpleName() + " " + rootException.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<RespondExceptionMessage> handlePersistenceException(PersistenceException e) {
        Throwable rootException = getRootThrowable(e);
        return new ResponseEntity<>(new RespondExceptionMessage(e.getClass().getSimpleName() + " " + e.getLocalizedMessage(),
                rootException.getClass().getSimpleName() + " " + rootException.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<RespondExceptionMessage> handleIllegalArgumentException(IllegalArgumentException e) {
        Throwable rootException = getRootThrowable(e);
        return new ResponseEntity<>(new RespondExceptionMessage(e.getClass().getSimpleName() + " " + e.getLocalizedMessage(),
                rootException.getClass().getSimpleName() + " " + rootException.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    private Throwable getRootThrowable(Exception e) {
        Throwable rootException = e;
        while (rootException.getCause() != null) {
            rootException = rootException.getCause();
        }
        return rootException;
    }
}
