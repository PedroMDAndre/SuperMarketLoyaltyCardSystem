package com.rs2.lcs.controllers;

import com.rs2.lcs.dto.UserIdPoint;
import com.rs2.lcs.dto.PurchaseDto;
import com.rs2.lcs.dto.RedeemDto;
import com.rs2.lcs.exceptions.InvalidOperationException;
import com.rs2.lcs.model.Operation;
import com.rs2.lcs.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OperationController {
    @Autowired
    OperationService operationService;

    @PostMapping(value = {"/operation/purchase"})
    public ResponseEntity<Object> purchase(@RequestBody PurchaseDto purchaseDto) {
        try {
            Operation operation = operationService.savePurchase(purchaseDto);
            return new ResponseEntity<>(operation, HttpStatus.CREATED);
        } catch (InvalidOperationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(value = {"/operation/redeem"})
    public ResponseEntity<Object> redeem(@RequestBody RedeemDto redeemDto) {
        try {
            Operation operation = operationService.saveRedeem(redeemDto);
            return new ResponseEntity<>(operation, HttpStatus.CREATED);
        } catch (InvalidOperationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = {"/operation/unclaimed-balance"})
    public ResponseEntity<Object> unclaimedOperationsBalance() {
        try {
            List<UserIdPoint> positiveBalancePoints = operationService.getPositiveBalancePoints();
            return new ResponseEntity<>(positiveBalancePoints, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
