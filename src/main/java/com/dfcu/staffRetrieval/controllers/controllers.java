package com.dfcu.staffRetrieval.controllers;

import com.dfcu.staffRegistration.models.EmployeeRegistrationRequest;
import com.dfcu.staffRegistration.repository.StaffRegistrationRepository;
import com.dfcu.staffRetrieval.services.StaffRetrievalService;
import com.dfcu.staffUpdate.models.UpdateEmployeeRequest;
import com.dfcu.staffUpdate.services.UpdateService;
import com.dfcu.utils.ResponseCode;
import com.dfcu.utils.ResponseCodeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
public class controllers {
    final StaffRetrievalService staffRetrievalService;
    final StaffRegistrationRepository staffRegistrationRepository;
    final UpdateService updateService;

    @Autowired
    public controllers(StaffRetrievalService staffRetrievalService, StaffRegistrationRepository staffRegistrationRepository, UpdateService updateService) {
        this.staffRetrievalService = staffRetrievalService;
        this.staffRegistrationRepository = staffRegistrationRepository;
        this.updateService = updateService;
    }

    // Endpoint to retrieve all employees or filter by surname
    @GetMapping("/employees")
    public ResponseEntity<?> getEmployees(@RequestHeader("Authorization") String authHeader,@RequestParam(required = false) String employeeNumber) {
        return staffRetrievalService.getEmployees(authHeader,employeeNumber);
    }

    @PutMapping("/employees/{employeeNumber}")
    public ResponseEntity<EmployeeRegistrationRequest> updateEmployee(@RequestHeader("Authorization") String authHeader,@PathVariable String employeeNumber,
                                                                      @RequestBody EmployeeRegistrationRequest updatedEmployee) {
        EmployeeRegistrationRequest employee = updateService.updateEmployee(authHeader,employeeNumber, updatedEmployee.getDateOfBirth(),
                updatedEmployee.getIdPhoto());
        if (employee != null) {
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}