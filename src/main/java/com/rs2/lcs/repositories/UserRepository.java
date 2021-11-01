package com.rs2.lcs.repositories;

import com.rs2.lcs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    long countByMobileNumber(Long mobileNumber);
    long countByIdCardNumber(Long idCardNumber);
}
