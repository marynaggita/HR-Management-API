package com.dfcu.staffRetrieval.services;

import com.dfcu.staffRegistration.models.EmployeeRegistrationRequest;
import com.dfcu.staffRegistration.repository.StaffRegistrationRepository;
import com.dfcu.utils.ResponseCode;
import com.dfcu.utils.ResponseCodeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StaffRetrievalService {
    @Autowired
    private final StaffRegistrationRepository staffRegistrationRepository;

    public StaffRetrievalService(StaffRegistrationRepository staffRegistrationRepository) {
        this.staffRegistrationRepository = staffRegistrationRepository;
    }
    // Method to retrieve all employees
    // Method to retrieve employees based on employee number or all employees if no number is provided
    public ResponseEntity<?> getEmployees(String employeeNumber) {
        List<EmployeeRegistrationRequest> employees;

        if (employeeNumber != null && !employeeNumber.isEmpty()) {
            employees = staffRegistrationRepository.findByEmployeeNumber(employeeNumber);
            if (employees.isEmpty()) {
                return ResponseCodeMap.retrievalResponse(ResponseCode.NOT_FOUND, "No employees found with the employee number: "+employeeNumber,null);

            } else {
                return ResponseCodeMap.retrievalResponse(ResponseCode.SUCCESS,"Employee Retrieved Successfully",employees);
            }
        } else {
            employees = staffRegistrationRepository.findAll();
            if (employees.isEmpty()) {
                return ResponseCodeMap.retrievalResponse(ResponseCode.NOT_FOUND, "No employees found in the system",null);
            } else {
                return ResponseCodeMap.retrievalResponse(ResponseCode.SUCCESS,"Employees Retrieved Successfully",employees);
            }
        }
    }
}
