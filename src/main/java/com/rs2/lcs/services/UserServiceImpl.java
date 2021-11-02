package com.rs2.lcs.services;

import com.rs2.lcs.dto.UserDto;
import com.rs2.lcs.exceptions.InvalidUserException;
import com.rs2.lcs.exceptions.UserNotFoundException;
import com.rs2.lcs.model.User;
import com.rs2.lcs.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static final String USER_NOT_FOUND = "User not found.";

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(UserDto userDto) throws InvalidUserException {
        Long mobileNumber = userDto.getMobileNumber();
        Long idCardNumber = userDto.getIdCardNumber();

        // Check if user is valid
        if (mobileNumber == null) {
            throw new InvalidUserException("A mobile number was not provided.");
        }
        if (idCardNumber == null) {
            throw new InvalidUserException("A card number was not provided.");
        }
        if (!isUniqueMobileNumber(mobileNumber)) {
            throw new InvalidUserException("This mobile number is being used by other user.");
        }
        if (!isUniqueIdCardNumber(idCardNumber)) {
            throw new InvalidUserException("This card number is being used by other user.");
        }

        User user = new User(
                userDto.getName(),
                userDto.getSurname(),
                mobileNumber,
                idCardNumber
        );

        userRepository.save(user);
        return user;
    }

    @Override
    public User findByUserId(Long id) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UserNotFoundException(USER_NOT_FOUND);
        }
    }

    @Override
    public User findByMobileNumber(Long mobileNumber) throws UserNotFoundException {
        User user = userRepository.findByMobileNumber(mobileNumber);
        if (user == null) {
            throw new UserNotFoundException(USER_NOT_FOUND);
        }
        return user;
    }

    @Override
    public User findByIdCardNumber(Long idCardNumber) throws UserNotFoundException {
        User user = userRepository.findByIdCardNumber(idCardNumber);
        if (user == null) {
            throw new UserNotFoundException(USER_NOT_FOUND);
        }
        return user;
    }

    private boolean isUniqueMobileNumber(Long mobileNumber) {
        long frequency = userRepository.countByMobileNumber(mobileNumber);
        return frequency == 0;
    }

    private boolean isUniqueIdCardNumber(Long idCardNumber) {
        long frequency = userRepository.countByIdCardNumber(idCardNumber);
        return frequency == 0;
    }
}
