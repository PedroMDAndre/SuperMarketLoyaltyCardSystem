package com.rs2.lcs.controllers;

import com.rs2.lcs.dto.UserIdPoint;
import com.rs2.lcs.dto.PurchaseDto;
import com.rs2.lcs.dto.RedeemDto;
import com.rs2.lcs.exceptions.InvalidOperationException;
import com.rs2.lcs.model.OperationTask;
import com.rs2.lcs.services.OperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Purchase Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful Purchase Register",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OperationTask.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid user id and/or cashier id",
                    content = @Content)})
    @PostMapping(value = {"/operation/purchase"})
    public ResponseEntity<Object> purchase(@RequestBody PurchaseDto purchaseDto) {
        try {
            OperationTask operationTask = operationService.savePurchase(purchaseDto);
            return new ResponseEntity<>(operationTask, HttpStatus.CREATED);
        } catch (InvalidOperationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Redeem Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful Redeem Register",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OperationTask.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid user id, cashier id or not enough points",
                    content = @Content)})
    @PostMapping(value = {"/operation/redeem"})
    public ResponseEntity<Object> redeem(@RequestBody RedeemDto redeemDto) {
        try {
            OperationTask operationTask = operationService.saveRedeem(redeemDto);
            return new ResponseEntity<>(operationTask, HttpStatus.CREATED);
        } catch (InvalidOperationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Get list of all unclaimed positive balances for existing users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of positive balances",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content)})
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
