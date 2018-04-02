package com.skilldealteam.skilldeal.exceptions;

public class ServiceException extends Exception {

    public String error = "Bad Request";

    public String message;

    public ServiceException(String message) {
        this.message = message;
    }

    public ServiceException(String error, String message) {
        this(message);
        this.error = error;
    }
}
