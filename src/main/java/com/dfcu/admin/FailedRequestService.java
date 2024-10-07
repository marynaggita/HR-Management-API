package com.dfcu.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FailedRequestService {

    private final FailedRequestRepository failedRequestRepository;

    @Autowired
    public FailedRequestService(FailedRequestRepository failedRequestRepository) {
        this.failedRequestRepository = failedRequestRepository;
    }

    public void logFailedRequest(String action, String error, int httpStatusCode, String endpoint) {
        FailedRequest failedRequest = new FailedRequest();
        failedRequest.setAction(action);
        failedRequest.setError(error);
        failedRequest.setHttpStatusCode(httpStatusCode);
        failedRequest.setEndpoint(endpoint);
        failedRequest.setCreatedAt(LocalDateTime.now());

        failedRequestRepository.save(failedRequest);
    }
}