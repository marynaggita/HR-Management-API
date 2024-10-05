package com.dfcu.staffRegistration.controllers;

import com.dfcu.staffRegistration.models.RegistrationRequest;
import com.dfcu.staffRegistration.services.CodeGenerationService;
import com.dfcu.staffRegistration.services.StaffRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@Validated
@RestController
public class Controller {
    final StaffRegistrationService registrationService;
    final CodeGenerationService generationService;
    @Autowired
    public Controller(StaffRegistrationService registrationService, CodeGenerationService generationService) {
        this.registrationService = registrationService;
        this.generationService = generationService;
    }
    @PostMapping("/GenerateEmployeeId")
    public ResponseEntity<String> generateEmployeeCode() {
        String employeeCode = generationService.generateAndSaveEmployeeCode(10);
        return ResponseEntity.ok(employeeCode);
    }

    @PostMapping(value ="/EmployeeRequest"  ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerEmployee(@RequestBody RegistrationRequest registrationRequest) throws IOException {
        System.out.println("Received RegistrationRequest: " + registrationRequest);
        return registrationService.registerEmployee(registrationRequest);
    }

}
