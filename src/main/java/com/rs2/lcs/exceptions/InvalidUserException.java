package com.rs2.lcs.exceptions;

public class InvalidUserException extends Exception {
    public InvalidUserException(String errorMessage){
        super(errorMessage);
    }
}
