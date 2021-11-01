package com.rs2.lcs.repositories;

import com.rs2.lcs.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    @Query(value = "SELECT SUM(point_balance) FROM operation WHERE user_id = ?1", nativeQuery = true)
    Long sumPointsById(Long userId);
}
