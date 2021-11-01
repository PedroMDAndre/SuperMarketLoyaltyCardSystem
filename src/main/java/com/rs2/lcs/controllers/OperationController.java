package com.rs2.lcs.controllers;

import com.rs2.lcs.model.User;
import com.rs2.lcs.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OperationController {
    @Autowired
    UserRepository userRepository;

    @PostMapping(value = {"/operation/purchase"})
    public String purchase(){
        User user = new User();
        userRepository.save(user);
        return user.toString();
    }

    @PostMapping(value = {"/operation/redeem"})
    public String redeem(){
        User user = new User();
        userRepository.save(user);
        return user.toString();
    }
}
