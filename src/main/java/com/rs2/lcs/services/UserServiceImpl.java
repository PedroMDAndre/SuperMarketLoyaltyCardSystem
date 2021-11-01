package com.rs2.lcs.services;

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
    public void save(User user) throws InvalidUserException{
        // Check if user is valid
        if (true) {
            throw new InvalidUserException();
        }
        userRepository.save(user);
    }
}
