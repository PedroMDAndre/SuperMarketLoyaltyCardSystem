package com.rs2.lcs.services;

import com.rs2.lcs.dto.UserDto;
import com.rs2.lcs.exceptions.InvalidUserException;
import com.rs2.lcs.model.User;

public interface UserService {
    void save(UserDto userDto) throws InvalidUserException;
    User findByUserId(Long id);
    User findByMobileNumber(Long MobileNumber);
    User findByIdCardNumber(Long idCardNumber);
}
