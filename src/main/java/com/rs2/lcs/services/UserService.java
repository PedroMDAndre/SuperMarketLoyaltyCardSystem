package com.rs2.lcs.services;

import com.rs2.lcs.dto.UserDto;
import com.rs2.lcs.exceptions.InvalidUserException;

public interface UserService {
    void save(UserDto userDto) throws InvalidUserException;
}
