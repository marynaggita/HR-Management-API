package com.dfcu.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FailedRequestRepository extends JpaRepository<FailedRequest, Long> {
    // Custom query to find recent failed requests
    List<FailedRequest> findTop10ByOrderByCreatedAtDesc();

    // Count failed requests by action
    long countByAction(String action);

    // Additional custom queries can be added as needed
}