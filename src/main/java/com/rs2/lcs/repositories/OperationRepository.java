package com.rs2.lcs.repositories;

import com.rs2.lcs.dto.UserIdPoint;
import com.rs2.lcs.model.OperationTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<OperationTask, Long> {
    @Query(value = "SELECT SUM(point_balance) FROM operation_task WHERE user_id = ?1",
            nativeQuery = true)
    Long sumPointsById(Long userId);

    @Query(value = "SELECT user_id as userId, SUM(point_balance) as balance FROM operation_task GROUP BY user_id HAVING balance > 0",
            nativeQuery = true)
    List<UserIdPoint> positiveBalancePoints();
}
