package com.rs2.lcs.services;

import com.rs2.lcs.dto.PurchaseDto;
import com.rs2.lcs.dto.RedeemDto;
import com.rs2.lcs.exceptions.InvalidOperationException;
import com.rs2.lcs.model.Operation;

public interface OperationService {
    Operation savePurchase(PurchaseDto purchaseDto) throws InvalidOperationException;
    Operation saveRedeem(RedeemDto redeemDto) throws InvalidOperationException;
}
