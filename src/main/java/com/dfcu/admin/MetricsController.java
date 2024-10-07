package com.dfcu.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MetricsController {

    private final MetricServiceFailed metricsService;

    public MetricsController(MetricServiceFailed metricsService) {
        this.metricsService = metricsService;
    }


    @GetMapping("/failedRequests")
    public ResponseEntity<?> getFailedRequestMetrics() {
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("totalFailedRequests", metricsService.getTotalFailedRequests());
        metrics.put("recentFailedRequests", metricsService.getRecentFailedRequests()); // Get the 10 most recent
        metrics.put("failedByType", metricsService.countFailedRequestsByType("generateCode"));
        return ResponseEntity.ok().body(metrics);
    }
}
