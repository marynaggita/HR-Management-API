package com.dfcu.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetricServiceFailed {

    private final FailedRequestRepository failedRequestRepository;



    public MetricServiceFailed(FailedRequestRepository failedRequestRepository) {
        this.failedRequestRepository = failedRequestRepository;
    }

    public long getTotalFailedRequests() {
        return failedRequestRepository.count();
    }

    public List<FailedRequest> getRecentFailedRequests() {
        return failedRequestRepository.findTop10ByOrderByCreatedAtDesc();
    }

    public long countFailedRequestsByType(String action) {
        return failedRequestRepository.countByAction(action);
    }
}
