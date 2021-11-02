package com.rs2.lcs.controllers;

import com.rs2.lcs.dto.UserDto;
import com.rs2.lcs.exceptions.InvalidUserException;
import com.rs2.lcs.exceptions.UserNotFoundException;
import com.rs2.lcs.model.User;
import com.rs2.lcs.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Operation(summary = "Add user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User added",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Mobile number and/or Id card number already registered or not given",
                    content = @Content)})
    @PostMapping(value = {"/user/add"})
    public ResponseEntity<Object> addUser(@RequestBody UserDto userDto) {
        try {
            User user = userService.save(userDto);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (InvalidUserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Get user by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "User not found.",
                    content = @Content)})
    @GetMapping(value = {"/user/id/{id}"})
    public ResponseEntity<Object> getUserById(@PathVariable(name = "id") Long id) {
        try {
            User user = userService.findByUserId(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Get user by its mobile number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "User not found.",
                    content = @Content)})
    @GetMapping(value = {"/user/phone/{mobileNumber}"})
    public ResponseEntity<Object> getUserByMobileNumber(@PathVariable(name = "mobileNumber") Long mobileNumber) {
        try {
            User user = userService.findByMobileNumber(mobileNumber);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Get user by its id card number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "User not found.",
                    content = @Content)})
    @GetMapping(value = {"/user/card/{cardId}"})
    public ResponseEntity<Object> getUserByIdCardNumber(@PathVariable(name = "cardId") Long cardId) {
        try {
            User user = userService.findByIdCardNumber(cardId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
