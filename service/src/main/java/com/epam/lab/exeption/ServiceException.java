package com.epam.lab.exeption;

public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}
