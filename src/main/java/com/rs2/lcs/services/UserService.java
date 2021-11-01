package com.rs2.lcs.services;

import com.rs2.lcs.exceptions.InvalidUserException;
import com.rs2.lcs.model.User;

public interface UserService {
    void save(User user) throws InvalidUserException;
}
