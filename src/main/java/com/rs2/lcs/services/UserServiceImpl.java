package com.rs2.lcs.services;

import com.rs2.lcs.dto.UserDto;
import com.rs2.lcs.exceptions.InvalidUserException;
import com.rs2.lcs.model.User;
import com.rs2.lcs.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public void save(UserDto userDto) throws InvalidUserException {
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

        userDto.setId(user.getId());
        userRepository.save(user);
    }

    @Override
    public User findByUserId(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findByMobileNumber(Long mobileNumber) {
        return userRepository.findByMobileNumber(mobileNumber);
    }

    @Override
    public User findByIdCardNumber(Long idCardNumber) {
        return userRepository.findByIdCardNumber(idCardNumber);
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
