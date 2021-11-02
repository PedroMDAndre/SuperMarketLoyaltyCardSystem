package com.rs2.lcs;

import com.rs2.lcs.dto.PurchaseDto;
import com.rs2.lcs.dto.RedeemDto;
import com.rs2.lcs.dto.UserIdPoint;
import com.rs2.lcs.dto.UserIdPointDto;
import com.rs2.lcs.exceptions.InvalidOperationException;
import com.rs2.lcs.model.OperationTask;
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
class OperationTaskServiceTest {
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

        OperationTask operationTask = new OperationTask(userId, cashierId, OperationEnum.PURCHASE.getDescription());
        operationTask.setCashSpent(cashSpent);
        operationTask.setPointBalance(points);

        PurchaseDto purchaseDto = new PurchaseDto(userId, cashierId, cashSpent);
        Mockito.when(userRepository.countById(userId)).thenReturn(1L);
        Mockito.when(cashierRepository.countById(cashierId)).thenReturn(1L);
        Mockito.when(operationRepository.save(Mockito.any(OperationTask.class))).thenReturn(operationTask);


        // Act
        OperationTask savedOperationTask = operationService.savePurchase(purchaseDto);

        // Assert
        Assertions.assertEquals(operationTask.getId(), savedOperationTask.getId());
        Assertions.assertEquals(operationTask.getUserId(), savedOperationTask.getUserId());
        Assertions.assertEquals(operationTask.getCashierId(), savedOperationTask.getCashierId());
        Assertions.assertEquals(operationTask.getOperationType(), savedOperationTask.getOperationType());
        Assertions.assertEquals(operationTask.getCashSpent(), savedOperationTask.getCashSpent());
        Assertions.assertEquals(operationTask.getPointBalance(), savedOperationTask.getPointBalance());
    }

    @Test
    void saveRedeemSuccessTest() throws InvalidOperationException {
        // Arrange
        Long userId = 1L;
        Long cashierId = 2L;
        double cashDiscount = 1.0;
        int points = -100;

        OperationTask operationTask = new OperationTask(userId, cashierId, OperationEnum.REDEEM.getDescription());
        operationTask.setCashDiscount(cashDiscount);
        operationTask.setPointBalance(points);

        RedeemDto redeemDto = new RedeemDto(userId, cashierId, false);
        Mockito.when(userRepository.countById(userId)).thenReturn(1L);
        Mockito.when(cashierRepository.countById(cashierId)).thenReturn(1L);
        Mockito.when(operationRepository.sumPointsById(userId)).thenReturn(100L);
        Mockito.when(operationRepository.save(Mockito.any(OperationTask.class))).thenReturn(operationTask);


        // Act
        OperationTask savedOperationTask = operationService.saveRedeem(redeemDto);

        // Assert
        Assertions.assertEquals(operationTask.getId(), savedOperationTask.getId());
        Assertions.assertEquals(operationTask.getUserId(), savedOperationTask.getUserId());
        Assertions.assertEquals(operationTask.getCashierId(), savedOperationTask.getCashierId());
        Assertions.assertEquals(operationTask.getOperationType(), savedOperationTask.getOperationType());
        Assertions.assertEquals(operationTask.getCashDiscount(), savedOperationTask.getCashDiscount());
        Assertions.assertEquals(operationTask.getPointBalance(), savedOperationTask.getPointBalance());
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
