package com.rs2.lcs.services;

import com.rs2.lcs.dto.UserDto;
import com.rs2.lcs.exceptions.InvalidUserException;
import com.rs2.lcs.model.User;

public interface UserService {
    User save(UserDto userDto) throws InvalidUserException;
    User findByUserId(Long id);
    User findByMobileNumber(Long mobileNumber);
    User findByIdCardNumber(Long idCardNumber);
}
