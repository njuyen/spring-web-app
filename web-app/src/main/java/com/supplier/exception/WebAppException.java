package com.supplier.exception;

public class WebAppException extends RuntimeException{

    private static final long serialVersionUID = -4286020057705515354L;
    
    public WebAppException(String message){
        super(message);
    }
}
