package com.rs2.lcs.controllers;

import com.rs2.lcs.dto.UserDto;
import com.rs2.lcs.exceptions.InvalidUserException;
import com.rs2.lcs.model.User;
import com.rs2.lcs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = {"/user/add"})
    public ResponseEntity<Object> addUser(@RequestBody UserDto userDto) {
        try {
            User user = userService.save(userDto);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (InvalidUserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = {"/user/id/{id}"})
    public User getUserById(@PathVariable(name = "id") Long id) {
        return userService.findByUserId(id);
    }

    @GetMapping(value = {"/user/phone/{mobileNumber}"})
    public User getUserByMobileNumber(@PathVariable(name = "mobileNumber") Long mobileNumber) {
        return userService.findByMobileNumber(mobileNumber);
    }

    @GetMapping(value = {"/user/card/{cardId}"})
    public User getUserByIdCardNumber(@PathVariable(name = "cardId") Long cardId) {
        return userService.findByIdCardNumber(cardId);
    }
}
