package com.rs2.lcs.services;

import com.rs2.lcs.dto.UserIdPoint;
import com.rs2.lcs.dto.PurchaseDto;
import com.rs2.lcs.dto.RedeemDto;
import com.rs2.lcs.exceptions.InvalidOperationException;
import com.rs2.lcs.model.OperationTask;

import java.util.List;

public interface OperationService {
    OperationTask savePurchase(PurchaseDto purchaseDto) throws InvalidOperationException;
    OperationTask saveRedeem(RedeemDto redeemDto) throws InvalidOperationException;
    List<UserIdPoint> getPositiveBalancePoints();
}
