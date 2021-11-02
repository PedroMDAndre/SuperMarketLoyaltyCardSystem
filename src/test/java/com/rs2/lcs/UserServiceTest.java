package com.rs2.lcs;

import com.rs2.lcs.dto.UserDto;
import com.rs2.lcs.exceptions.InvalidUserException;
import com.rs2.lcs.model.User;
import com.rs2.lcs.repositories.UserRepository;
import com.rs2.lcs.services.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Autowired
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void initUseCase() {

    }

    @Test
    void saveTestSuccess() throws InvalidUserException {
        // Arrange
        String name = "James";
        String surname = "Who";
        Long mobileNumber = 123753126L;
        Long idCardNumber = 758412532L;

        User user = new User(name, surname, mobileNumber, idCardNumber);

        UserDto userDto = new UserDto(name, surname, mobileNumber, idCardNumber);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        // Act
        User savedUser = userService.save(userDto);

        // Assert
        Assertions.assertEquals(user.getId(), savedUser.getId());
        Assertions.assertEquals(user.getName(), savedUser.getName());
        Assertions.assertEquals(user.getMobileNumber(), savedUser.getMobileNumber());
        Assertions.assertEquals(user.getIdCardNumber(), savedUser.getIdCardNumber());
    }

    @Test
    void saveTestFailNoMobileNumber() {
        // Arrange
        String name = "James";
        String surname = "Who";
        Long mobileNumber = null;
        Long idCardNumber = 758412532L;

        UserDto userDto = new UserDto(name, surname, mobileNumber, idCardNumber);

        // Assert
        Assertions.assertThrows(InvalidUserException.class, () -> {
            // Act
            userService.save(userDto);
        });
    }

    @Test
    void saveTestFailNoIdCard() {
        // Arrange
        String name = "James";
        String surname = "Who";
        Long mobileNumber = 123753126L;
        Long idCardNumber = null;

        UserDto userDto = new UserDto(name, surname, mobileNumber, idCardNumber);

        // Assert
        Assertions.assertThrows(InvalidUserException.class, () -> {
            // Act
            userService.save(userDto);
        });
    }

    @Test
    void saveTestFailSameMobileNumber() {
        // Arrange
        String name = "James";
        String surname = "Who";
        Long mobileNumber = 123753126L;
        Long idCardNumber = 758412532L;

        UserDto userDto = new UserDto(name, surname, mobileNumber, idCardNumber);

        Mockito.when(userRepository.countByMobileNumber(mobileNumber)).thenReturn(1L);

        // Assert
        Assertions.assertThrows(InvalidUserException.class, () -> {
            // Act
            userService.save(userDto);
        });
    }

    @Test
    void saveTestFailSameIdCardNumber() {
        // Arrange
        String name = "James";
        String surname = "Who";
        Long mobileNumber = 123753126L;
        Long idCardNumber = 758412532L;

        UserDto userDto = new UserDto(name, surname, mobileNumber, idCardNumber);

        Mockito.when(userRepository.countByIdCardNumber(idCardNumber)).thenReturn(1L);

        // Assert
        Assertions.assertThrows(InvalidUserException.class, () -> {
            // Act
            userService.save(userDto);
        });
    }

    @Test
    void findByUserIdSuccessTest() {
        // Arrange
        Long userId = 1L;
        String name = "James";
        String surname = "Who";
        Long mobileNumber = 123753126L;
        Long idCardNumber = 758412532L;

        User expectedUser = new User(name, surname, mobileNumber, idCardNumber);
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));

        // Act
        User returnedUser = userService.findByUserId(userId);

        // Assert
        Assertions.assertEquals(expectedUser, returnedUser);
    }

    @Test
    void findByMobileNumberSuccessTest() {
        // Arrange
        String name = "James";
        String surname = "Who";
        Long mobileNumber = 123753126L;
        Long idCardNumber = 758412532L;

        User expectedUser = new User(name, surname, mobileNumber, idCardNumber);
        Mockito.when(userRepository.findByMobileNumber(mobileNumber)).thenReturn(expectedUser);

        // Act
        User returnedUser = userService.findByMobileNumber(mobileNumber);

        // Assert
        Assertions.assertEquals(expectedUser, returnedUser);
    }

    @Test
    void findByIdCardNumberSuccessTest() {
        // Arrange
        String name = "James";
        String surname = "Who";
        Long mobileNumber = 123753126L;
        Long idCardNumber = 758412532L;

        User expectedUser = new User(name, surname, mobileNumber, idCardNumber);
        Mockito.when(userRepository.findByIdCardNumber(idCardNumber)).thenReturn(expectedUser);

        // Act
        User returnedUser = userService.findByIdCardNumber(idCardNumber);

        // Assert
        Assertions.assertEquals(expectedUser, returnedUser);
    }
}
