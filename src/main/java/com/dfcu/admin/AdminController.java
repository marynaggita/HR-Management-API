package com.dfcu.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AdminController {

    @Autowired
    private final EmployeeService employeeService;
    private final EmployeeCodeService employeeCodeService;
    private final FailedRequestService failedRequestService;

    public AdminController(EmployeeService employeeService, EmployeeCodeService employeeCodeService, FailedRequestService failedRequestService) {
        this.employeeService = employeeService;
        this.employeeCodeService = employeeCodeService;
        this.failedRequestService = failedRequestService;
    }

    @GetMapping("/adminMetrics")
    public ResponseEntity<Map<String, Long>> getMetrics() {
        Map<String, Long> metrics = new HashMap<>();
        metrics.put("totalEmployees", employeeService.getTotalEmployees());
        metrics.put("totalEmployeeCodes", employeeCodeService.getTotalEmployeeCodesGenerated());
        return ResponseEntity.ok(metrics);
    }

    @PostMapping("/logFailedRequest")
    public ResponseEntity<?> logFailedRequest(@RequestBody FailedRequest failedRequestDto) {
        failedRequestService.logFailedRequest(
                failedRequestDto.getAction(),
                failedRequestDto.getError(),
                failedRequestDto.getHttpStatusCode(),
                failedRequestDto.getEndpoint()
        );
        return ResponseEntity.ok().body("Failed request logged successfully.");
    }
}

