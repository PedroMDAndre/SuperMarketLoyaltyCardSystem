package com.rs2.lcs.controllers;

import com.rs2.lcs.dto.OperationDto;
import com.rs2.lcs.dto.UserDto;
import com.rs2.lcs.exceptions.InvalidOperationException;
import com.rs2.lcs.exceptions.InvalidUserException;
import com.rs2.lcs.model.OperationEnum;
import com.rs2.lcs.model.User;
import com.rs2.lcs.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OperationController {
    @Autowired
    OperationService operationService;

    @PostMapping(value = {"/operation/purchase"})
    public ResponseEntity<Object> purchase(@RequestBody OperationDto operationDto){
        operationDto.setOperationType(OperationEnum.PURCHASE.getDescription());
        try {
            operationService.save(operationDto, OperationEnum.PURCHASE);
            return new ResponseEntity<>(operationDto, HttpStatus.CREATED);
        } catch (InvalidOperationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(value = {"/operation/redeem"})
    public ResponseEntity<Object> redeem(@RequestBody OperationDto operationDto){
        operationDto.setOperationType(OperationEnum.REDEEM.getDescription());
        try {
            operationService.save(operationDto, OperationEnum.REDEEM);
            return new ResponseEntity<>(operationDto, HttpStatus.CREATED);
        } catch (InvalidOperationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
