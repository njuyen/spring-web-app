package com.supplier.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.supplier.domain.User;
import com.supplier.service.UserService;
import com.supplier.web.form.UserCreateDto;
import com.supplier.web.form.UserLoginDto;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value ="/all", method = RequestMethod.GET)
    public List<User> getAll(){
        return userService.getAll();
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean login(@Valid @RequestBody UserLoginDto userLoginDto){
        return userService.verify(userLoginDto.getUsername(), userLoginDto.getPassword());
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Long register(@Valid @RequestBody UserCreateDto userCreateDto){
        return userService.create(userCreateDto.getUsername(), userCreateDto.getPassword());
    }
}
