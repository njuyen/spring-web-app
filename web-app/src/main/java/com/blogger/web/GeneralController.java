package com.blogger.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {
    
    @RequestMapping("/")
    public String index(){
        return "Welcom to blogger";
    }

    
    @RequestMapping(value = ExceptionController.ERR_PATH)
    public String error(HttpServletRequest request, HttpServletResponse response) {
        return "Error code: " + response.getStatus();
    }
}
