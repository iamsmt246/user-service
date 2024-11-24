package com.realtimeStockPortfolio.userService.service;

import com.realtimeStockPortfolio.userService.model.User;
import com.realtimeStockPortfolio.userService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.w3c.dom.stylesheets.LinkStyle;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public Optional<User> getUser(String username){
        return userRepository.findByUsername(username);
    }

    public User registerUser(User user){
        User registeredUser = userRepository.save(user);

        return  registeredUser;
    }
}
