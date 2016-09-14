package com.blogger.web.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionController implements ErrorController{
    public static final String ERR_PATH = "/error";
    
    @Override
    public String getErrorPath() {
        return ERR_PATH;
    }
    
    @ExceptionHandler({Exception.class, RuntimeException.class})
    public String exceptionsCover(Exception e) {
        return e.getMessage();
    }

}
