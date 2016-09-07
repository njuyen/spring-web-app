package com.supplier.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.supplier.config.auth.token.Token;
import com.supplier.config.auth.token.TokenProvider;
import com.supplier.domain.User;
import com.supplier.service.UserService;
import com.supplier.web.form.UserCreateDto;
import com.supplier.web.form.UserLoginDto;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;
    
    @RequestMapping(value ="/all", method = RequestMethod.GET)
    public List<User> getAll(){
        return userService.getAll();
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Token login(@Valid @RequestBody UserLoginDto userLoginDto){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails user = userDetailsService.loadUserByUsername(userLoginDto.getUsername());
        return tokenProvider.createToken(user);
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Long register(@Valid @RequestBody UserCreateDto userCreateDto){
        return userService.create(userCreateDto.getUsername(), userCreateDto.getPassword());
    }
}
