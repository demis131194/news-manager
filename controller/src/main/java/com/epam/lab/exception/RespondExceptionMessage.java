package com.epam.lab.exception;

public class RespondExceptionMessage {
    private String exception;
    private String rootException;

    public RespondExceptionMessage(String exception, String rootException) {
        this.exception = exception;
        this.rootException = rootException;
    }

    public String getException() {
        return exception;
    }

    public String getRootException() {
        return rootException;
    }
}
