package com.rs2.lcs.services;

import com.rs2.lcs.dto.OperationDto;
import com.rs2.lcs.exceptions.InvalidOperationException;
import com.rs2.lcs.model.Operation;
import com.rs2.lcs.model.OperationEnum;
import com.rs2.lcs.repositories.OperationRepository;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImpl implements OperationService {
    private static final int DISCOUNT_CASH = 1; // EUR
    private static final int DISCOUNT_PER_POINTS = -100; // Points
    private static final int PURCHASE_PER_CASH = 50; // EUR
    private static final int PURCHASE_POINTS = 10; // Points

    private OperationRepository operationRepository;

    public void save(OperationDto operationDto, OperationEnum operationType) throws InvalidOperationException {
        Long userId = operationDto.getUserId();
        Long cashierId = operationDto.getCashierId();

        Operation operation = new Operation(userId, cashierId, operationType.getDescription());

        if (operationType.equals(OperationEnum.PURCHASE)) {
            double cashSpent = operationDto.getCashSpent();
            int purchasePoints = purchasePoints(cashSpent);

            operation.setOperationType(OperationEnum.PURCHASE.getDescription());
            operation.setCashSpent(cashSpent);
            operation.setPointBalance(purchasePoints);
        } else if (operationType.equals(OperationEnum.REDEEM)) {
            if (!isPossibleToRedeemPoints(userId)) {
                throw new InvalidOperationException("Not enough points.");
            }

            boolean deliveredWaterPacket = operationDto.isDeliveredWaterPacket();

            operation.setOperationType(OperationEnum.REDEEM.getDescription());
            operation.setPointBalance(DISCOUNT_PER_POINTS);
            operation.setDeliveredWaterPacket(deliveredWaterPacket);
            if(!deliveredWaterPacket){
                operation.setCashDiscount(DISCOUNT_CASH);
            }
        } else {
            throw new InvalidOperationException("Operation is not valid.");
        }

        operationDto.setDateTime(operation.getDateTime());
        operationRepository.save(operation);
    }

    private boolean isPossibleToRedeemPoints(Long userId) {
        return operationRepository.sumPointsById(userId) >= DISCOUNT_PER_POINTS;
    }

    private int purchasePoints(double value) {
        return ((int) (value / PURCHASE_PER_CASH)) * PURCHASE_POINTS;
    }

}
