package com.rs2.lcs.services;

import com.rs2.lcs.dto.UserIdPoint;
import com.rs2.lcs.dto.PurchaseDto;
import com.rs2.lcs.dto.RedeemDto;
import com.rs2.lcs.exceptions.InvalidOperationException;
import com.rs2.lcs.model.OperationTask;
import com.rs2.lcs.model.OperationEnum;
import com.rs2.lcs.repositories.CashierRepository;
import com.rs2.lcs.repositories.OperationRepository;
import com.rs2.lcs.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {
    private static final int DISCOUNT_CASH = 1;             // EUR
    private static final int DISCOUNT_PER_POINTS = -100;    // Points
    private static final int PURCHASE_PER_CASH = 50;        // EUR
    private static final int PURCHASE_POINTS = 10;          // Points

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CashierRepository cashierRepository;

    @Override
    public OperationTask savePurchase(PurchaseDto purchaseDto) throws InvalidOperationException {
        Long userId = purchaseDto.getUserId();
        Long cashierId = purchaseDto.getCashierId();

        checkValidUserAndCashier(userId, cashierId);

        OperationTask operationTask = new OperationTask(userId, cashierId, OperationEnum.PURCHASE.getDescription());

        double cashSpent = purchaseDto.getCashSpent();
        int purchasePoints = purchasePoints(cashSpent);

        operationTask.setCashSpent(cashSpent);
        operationTask.setPointBalance(purchasePoints);

        operationRepository.save(operationTask);
        return operationTask;
    }

    @Override
    public OperationTask saveRedeem(RedeemDto redeemDto) throws InvalidOperationException {
        Long userId = redeemDto.getUserId();
        Long cashierId = redeemDto.getCashierId();

        checkValidUserAndCashier(userId, cashierId);

        if (!isPossibleToRedeemPoints(userId)) {
            throw new InvalidOperationException("Not enough points.");
        }

        OperationTask operationTask = new OperationTask(userId, cashierId, OperationEnum.REDEEM.getDescription());

        boolean deliveredWaterPacket = redeemDto.isDeliveredWaterPacket();

        operationTask.setPointBalance(DISCOUNT_PER_POINTS);
        operationTask.setDeliveredWaterPacket(deliveredWaterPacket);
        if (!deliveredWaterPacket) {
            operationTask.setCashDiscount(DISCOUNT_CASH);
        }

        operationRepository.save(operationTask);
        return operationTask;
    }

    @Override
    public List<UserIdPoint> getPositiveBalancePoints() {
        return operationRepository.positiveBalancePoints();
    }

    private boolean isPossibleToRedeemPoints(Long userId) {
        return operationRepository.sumPointsById(userId) >= -DISCOUNT_PER_POINTS;
    }

    private int purchasePoints(double value) {
        return ((int) (value / PURCHASE_PER_CASH)) * PURCHASE_POINTS;
    }

    private boolean isValidUserId(Long id) {
        return userRepository.countById(id) == 1L;
    }

    private boolean isValidCashierId(Long id) {
        return cashierRepository.countById(id) == 1L;
    }

    private void checkValidUserAndCashier(Long userId, Long cashierId) throws InvalidOperationException {
        if (!isValidUserId(userId)) {
            throw new InvalidOperationException("User id doesn't exists in the database.");
        }
        if (!isValidCashierId(cashierId)) {
            throw new InvalidOperationException("Cashier id doesn't exists in the database.");
        }
    }
}
