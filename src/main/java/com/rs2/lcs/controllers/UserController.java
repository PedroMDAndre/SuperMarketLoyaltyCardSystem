package com.rs2.lcs.controllers;

import com.rs2.lcs.exceptions.InvalidUserException;
import com.rs2.lcs.model.User;
import com.rs2.lcs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = {"/user/add"})
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try {
            userService.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (InvalidUserException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
