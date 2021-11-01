package com.rs2.lcs.repositories;

import com.rs2.lcs.model.Cashier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashierRepository extends JpaRepository<Cashier, Long> {
    /**
     * Get a list of all
     * */
    Cashier findAllById(Long id);
}
