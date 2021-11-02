package com.rs2.lcs;

import com.rs2.lcs.dto.PurchaseDto;
import com.rs2.lcs.exceptions.InvalidOperationException;
import com.rs2.lcs.model.Operation;
import com.rs2.lcs.model.OperationEnum;
import com.rs2.lcs.model.User;
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
    void saveRedeemSuccessTest() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    void getPositiveBalancePointsSuccessTest() {
        // Arrange

        // Act

        // Assert
    }
}
