package com.epam.lab.exception;

import com.epam.lab.exeption.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.PersistenceException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<RespondExceptionMessage> handleServiceException(ServiceException e) {
        logger.warn("ServiceException : ", e);
        Throwable rootException = getRootThrowable(e);
        return new ResponseEntity<>(new RespondExceptionMessage(rootException.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<RespondExceptionMessage> handlePersistenceException(PersistenceException e) {
        logger.error("PersistenceException : ", e);
        Throwable rootException = getRootThrowable(e);
        return new ResponseEntity<>(new RespondExceptionMessage(rootException.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<RespondExceptionMessage> handleIllegalArgumentException(IllegalArgumentException e) {
        logger.warn("IllegalArgumentException : ", e);
        Throwable rootException = getRootThrowable(e);
        return new ResponseEntity<>(new RespondExceptionMessage(rootException.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RepositoryException.class)
    public ResponseEntity<RespondExceptionMessage> handleRepositoryException(RepositoryException e) {
        logger.warn("RepositoryException : ", e);
        Throwable rootException = getRootThrowable(e);
        return new ResponseEntity<>(new RespondExceptionMessage(rootException.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    private Throwable getRootThrowable(Exception e) {
        Throwable rootException = e;
        while (rootException.getCause() != null) {
            rootException = rootException.getCause();
        }
        return rootException;
    }
}
