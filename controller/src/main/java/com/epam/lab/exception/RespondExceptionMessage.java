package com.epam.lab.exception;

public class RespondExceptionMessage {
    private String localeExceptionMessage;

    public RespondExceptionMessage(String localeExceptionMessage) {
        this.localeExceptionMessage = localeExceptionMessage;
    }

    public String getLocaleExceptionMessage() {
        return localeExceptionMessage;
    }
}
