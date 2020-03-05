package com.epam.lab.exception;

import com.epam.lab.exeption.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

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

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<RespondExceptionMessage> handleRepositoryException(ConstraintViolationException e) {
        logger.warn("RepositoryException : ", e);
        Throwable rootException = getRootThrowable(e);
        return new ResponseEntity<>(new RespondExceptionMessage(rootException.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);
    }

    private Throwable getRootThrowable(Exception e) {
        Throwable rootException = e;
        while (rootException.getCause() != null) {
            rootException = rootException.getCause();
        }
        return rootException;
    }
}
