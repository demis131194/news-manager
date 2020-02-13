package com.epam.lab.controller;

import com.epam.lab.exeption.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataAccessException.class)
    public @ResponseBody String handleDataAccessException(DataAccessException e) {
        logger.error(e);
        return "DataAccessException : " + e.getLocalizedMessage();
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServiceException.class)
    public @ResponseBody String handleServiceException(ServiceException e) {
        logger.warn(e);
        return "ServiceException : " + e.getLocalizedMessage();
    }
}
