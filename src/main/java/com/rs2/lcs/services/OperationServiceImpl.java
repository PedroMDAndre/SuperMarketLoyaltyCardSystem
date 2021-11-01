package com.rs2.lcs.services;

import com.rs2.lcs.dto.OperationDto;
import com.rs2.lcs.exceptions.InvalidOperationException;
import com.rs2.lcs.model.Operation;
import com.rs2.lcs.model.OperationEnum;
import com.rs2.lcs.repositories.OperationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OperationServiceImpl implements OperationService {
    private static final int DISCOUNT_CASH = 1; // EUR
    private static final int DISCOUNT_PER_POINTS = 100; // Points
    private static final int PURCHASE_PER_CASH = 50; // EUR
    private static final int PURCHASE_POINTS = 10; // Points

    private OperationRepository operationRepository;

    public void save(OperationDto operationDto, OperationEnum operationType) throws InvalidOperationException {
        Long userId = operationDto.getUserId();
        Long cashierId = operationDto.getCashierId();

        // Check if Operation is valid

        Operation operation = new Operation(userId, cashierId, operationType.getDescription());

        if (operationType.equals(OperationEnum.PURCHASE)) {

            //private int pointBalance = 0;
            //private double cashSpent = 0;
            //private double cashDiscount = 0;
            //private boolean deliveredWaterPacket = false;

            //operation.setOperationType(OperationEnum.PURCHASE.getDescription());
            //operation.setPointBalance(purchasePoints(operation.getCashSpent()));

        } else if (operationType.equals(OperationEnum.REDEEM)) {
            operation.setOperationType(OperationEnum.REDEEM.getDescription());

        } else {
            throw new InvalidOperationException("Operation is not valid.");
        }

        operationRepository.save(operation);
    }

    private boolean isPossibleToRedeemPoints(int points) {
        return false;
    }

    private int purchasePoints(double value) {
        return ((int)(value / PURCHASE_PER_CASH)) * PURCHASE_POINTS;
    }

}
