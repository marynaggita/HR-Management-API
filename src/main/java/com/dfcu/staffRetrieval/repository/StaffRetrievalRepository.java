package com.dfcu.staffRetrieval.repository;

import com.dfcu.staffRetrieval.models.RetrievalRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRetrievalRepository extends JpaRepository<RetrievalRequest,Long> {
    List<RetrievalRequest> findByName(String name);
}
