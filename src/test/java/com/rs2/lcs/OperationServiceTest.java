package com.rs2.lcs;

import com.rs2.lcs.dto.PurchaseDto;
import com.rs2.lcs.dto.RedeemDto;
import com.rs2.lcs.dto.UserIdPoint;
import com.rs2.lcs.dto.UserIdPointDto;
import com.rs2.lcs.exceptions.InvalidOperationException;
import com.rs2.lcs.model.Operation;
import com.rs2.lcs.model.OperationEnum;
import com.rs2.lcs.repositories.CashierRepository;
import com.rs2.lcs.repositories.OperationRepository;
import com.rs2.lcs.repositories.UserRepository;
import com.rs2.lcs.services.OperationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class OperationServiceTest {
    @Autowired
    @InjectMocks
    private OperationServiceImpl operationService;

    @Mock
    private OperationRepository operationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CashierRepository cashierRepository;

    @Test
    void savePurchaseSuccessTest() throws InvalidOperationException {
        // Arrange
        Long userId = 1L;
        Long cashierId = 2L;
        double cashSpent = 50.0;
        int points = 10;

        Operation operation = new Operation(userId, cashierId, OperationEnum.PURCHASE.getDescription());
        operation.setCashSpent(cashSpent);
        operation.setPointBalance(points);

        PurchaseDto purchaseDto = new PurchaseDto(userId, cashierId, cashSpent);
        Mockito.when(userRepository.countById(userId)).thenReturn(1L);
        Mockito.when(cashierRepository.countById(cashierId)).thenReturn(1L);
        Mockito.when(operationRepository.save(Mockito.any(Operation.class))).thenReturn(operation);


        // Act
        Operation savedOperation = operationService.savePurchase(purchaseDto);

        // Assert
        Assertions.assertEquals(operation.getId(), savedOperation.getId());
        Assertions.assertEquals(operation.getUserId(), savedOperation.getUserId());
        Assertions.assertEquals(operation.getCashierId(), savedOperation.getCashierId());
        Assertions.assertEquals(operation.getOperationType(), savedOperation.getOperationType());
        Assertions.assertEquals(operation.getCashSpent(), savedOperation.getCashSpent());
        Assertions.assertEquals(operation.getPointBalance(), savedOperation.getPointBalance());
    }

    @Test
    void saveRedeemSuccessTest() throws InvalidOperationException {
        // Arrange
        Long userId = 1L;
        Long cashierId = 2L;
        double cashDiscount = 1.0;
        int points = -100;

        Operation operation = new Operation(userId, cashierId, OperationEnum.REDEEM.getDescription());
        operation.setCashDiscount(cashDiscount);
        operation.setPointBalance(points);

        RedeemDto redeemDto = new RedeemDto(userId, cashierId, false);
        Mockito.when(userRepository.countById(userId)).thenReturn(1L);
        Mockito.when(cashierRepository.countById(cashierId)).thenReturn(1L);
        Mockito.when(operationRepository.sumPointsById(userId)).thenReturn(100L);
        Mockito.when(operationRepository.save(Mockito.any(Operation.class))).thenReturn(operation);


        // Act
        Operation savedOperation = operationService.saveRedeem(redeemDto);

        // Assert
        Assertions.assertEquals(operation.getId(), savedOperation.getId());
        Assertions.assertEquals(operation.getUserId(), savedOperation.getUserId());
        Assertions.assertEquals(operation.getCashierId(), savedOperation.getCashierId());
        Assertions.assertEquals(operation.getOperationType(), savedOperation.getOperationType());
        Assertions.assertEquals(operation.getCashDiscount(), savedOperation.getCashDiscount());
        Assertions.assertEquals(operation.getPointBalance(), savedOperation.getPointBalance());
    }

    @Test
    void saveRedeemFailInvalidUserIdTest() {
        // Arrange
        Long userId = 1L;
        Long cashierId = 2L;

        RedeemDto redeemDto = new RedeemDto(userId, cashierId, false);

        Mockito.when(userRepository.countById(userId)).thenReturn(0L);

        // Assert
        Assertions.assertThrows(InvalidOperationException.class, () -> {
            // Act
            operationService.saveRedeem(redeemDto);
        });
    }

    @Test
    void saveRedeemFailInvalidCashierIdTest() {
        // Arrange
        Long userId = 1L;
        Long cashierId = 2L;

        RedeemDto redeemDto = new RedeemDto(userId, cashierId, false);

        Mockito.when(userRepository.countById(userId)).thenReturn(1L);
        Mockito.when(cashierRepository.countById(cashierId)).thenReturn(0L);

        // Assert
        Assertions.assertThrows(InvalidOperationException.class, () -> {
            // Act
            operationService.saveRedeem(redeemDto);
        });
    }

    @Test
    void saveRedeemFailNotEnoughPointsTest() {
        // Arrange
        Long userId = 1L;
        Long cashierId = 2L;

        RedeemDto redeemDto = new RedeemDto(userId, cashierId, false);

        Mockito.when(userRepository.countById(userId)).thenReturn(1L);
        Mockito.when(cashierRepository.countById(cashierId)).thenReturn(1L);
        Mockito.when(operationRepository.sumPointsById(userId)).thenReturn(0L);

        // Assert
        Assertions.assertThrows(InvalidOperationException.class, () -> {
            // Act
            operationService.saveRedeem(redeemDto);
        });
    }

    @Test
    void getPositiveBalancePointsSuccessTest() {
        // Arrange
        UserIdPointDto[] userIdPointDtoArray = {new UserIdPointDto(1L, 100), new UserIdPointDto(1L, 100)};
        List<UserIdPoint> expectedUserIdPointList = Arrays.asList(userIdPointDtoArray);

        Mockito.when(operationRepository.positiveBalancePoints()).thenReturn(expectedUserIdPointList);

        // Act
        List<UserIdPoint> userIdPointList = operationService.getPositiveBalancePoints();

        // Assert
        Assertions.assertEquals(expectedUserIdPointList, userIdPointList);
    }
}
