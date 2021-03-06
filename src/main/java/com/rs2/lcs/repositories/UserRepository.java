package com.rs2.lcs.repositories;

import com.rs2.lcs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    long countById(Long id);
    long countByMobileNumber(Long mobileNumber);
    long countByIdCardNumber(Long idCardNumber);
    User findByMobileNumber(Long mobileNumber);
    User findByIdCardNumber(Long idCardNumber);
}
