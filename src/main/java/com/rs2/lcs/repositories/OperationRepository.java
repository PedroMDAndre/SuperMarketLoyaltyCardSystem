package com.rs2.lcs.repositories;

import com.rs2.lcs.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
