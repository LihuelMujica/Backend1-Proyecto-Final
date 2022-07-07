package com.digitalhouse.clinic.exception;

public class ResourceAlreadyExistsException extends Exception{
    public ResourceAlreadyExistsException(String message){
        super(message);
    }
}
