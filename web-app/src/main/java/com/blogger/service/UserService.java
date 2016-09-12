package com.blogger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogger.domain.User;
import com.blogger.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserById(long id) {
        return userRepository.findOneById(id);
    }

    public List<User> getAll() {
        return new ArrayList<User>(userRepository.findAll());
    }

    public boolean verify(String username, String password) {
        Optional<User> user = userRepository.findOneByName(username);
        return user.isPresent() && user.get().getPassword().equals(password);
    }

    public Long create(String username, String password) {
        return userRepository.create(username, password);
    }

}
