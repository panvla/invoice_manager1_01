package com.vladimirpandurov.invoice_manager01.exception;

public class ApiException extends RuntimeException{

    public ApiException(String message){
        super(message);
    }

}
