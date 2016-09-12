package com.supplier.config.auth;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.supplier.exception.WebAppException;
import com.supplier.repository.UserRepository;

@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        String lowercaseUsername = username.toLowerCase();
        Optional<com.supplier.domain.User> user = userRepository.findOneByName(lowercaseUsername);
        return user.map(u -> {
            List authorities = 
                    Collections.singletonList(new SimpleGrantedAuthority(u.getRole().name()));
            return new User(username, u.getPassword(), authorities);
        }).orElseThrow(() -> new WebAppException("Authentication failed!"));
    }
}
