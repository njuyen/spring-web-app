package com.blogger.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blogger.domain.User;
import com.blogger.service.UserService;
import com.blogger.web.form.UserCreateDto;
import com.blogger.web.form.UserLoginDto;

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
    public Boolean login(@Valid @RequestBody UserLoginDto userLoginDto){
        return false;
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Long register(@Valid @RequestBody UserCreateDto userCreateDto){
        return userService.create(userCreateDto.getUsername(), userCreateDto.getPassword());
    }
}
