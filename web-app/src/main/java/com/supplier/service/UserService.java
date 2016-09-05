package com.supplier.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supplier.domain.User;
import com.supplier.repository.UserRepository;

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
        return userRepository.create(new User(null, username, password, User.Role.USER));
    }

}
