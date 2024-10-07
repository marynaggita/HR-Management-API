package com.dfcu.staffRegistration.services;

import com.dfcu.authentication.AuthService;
import com.dfcu.staffRegistration.models.EmployeeCodeRequest;
import com.dfcu.staffRegistration.models.EmployeeRegistrationRequest;
import com.dfcu.staffRegistration.models.RegistrationRequest;
import com.dfcu.staffRegistration.repository.EmployeeCodeRepository;
import com.dfcu.staffRegistration.repository.StaffRegistrationRepository;
import com.dfcu.utils.ResponseCode;
import com.dfcu.utils.ResponseCodeMap;
import com.dfcu.utils.ServiceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@Slf4j
public class StaffRegistrationService {

    private final StaffRegistrationRepository staffRegistrationRepository;
    private final EmployeeCodeRepository employeeCodeRepository;
    private final CodeGenerationService codeGenerationService;
    private final ServiceUtils serviceUtils;
    private final AuthService authService;

    @Autowired
    public StaffRegistrationService(StaffRegistrationRepository staffRegistrationRepository, EmployeeCodeRepository employeeCodeRepository, CodeGenerationService codeGenerationService, ServiceUtils serviceUtils, AuthService authService) {
        this.staffRegistrationRepository = staffRegistrationRepository;
        this.employeeCodeRepository = employeeCodeRepository;
        this.codeGenerationService = codeGenerationService;
        this.serviceUtils = serviceUtils;
        this.authService = authService;
    }

    public ResponseEntity<?> registerEmployee(RegistrationRequest registrationRequest,String username,String password) throws IOException {
        if (!authService.authenticate(username, password)) {
            throw new RuntimeException("Authentication failed: Invalid username or password");
        }
        String employeeCode = registrationRequest.getEmployeeCode();
        log.info("RegistrationRequest body: " + registrationRequest);

        // Check if the employee code exists in the repository
        EmployeeCodeRequest employeeCodeRequest = employeeCodeRepository.findByEmployeeCode(employeeCode);
        if (employeeCodeRequest == null) {
            return ResponseCodeMap.response(ResponseCode.NOT_FOUND, "Employee code does not exist");
        }

        // Check if the employee code has already been used
        if (employeeCodeRequest.isUsed()) {
            return ResponseCodeMap.response(ResponseCode.BAD_REQUEST, "Employee code has already been used");
        }

        // Generate employee number (generate random last 3 digits)
        String employeeNumber = "DFCU" + codeGenerationService.generateAndSaveEmployeeNumber(3);

        // Create and populate the EmployeeRegistrationRequest object
        EmployeeRegistrationRequest employeeRegistrationRequest = new EmployeeRegistrationRequest();
        employeeRegistrationRequest.setEmployeeNumber(employeeNumber);
        employeeRegistrationRequest.setSurname(registrationRequest.getSurname());
        employeeRegistrationRequest.setOtherName(registrationRequest.getOtherName());

        String date = serviceUtils.convertDateFormat(String.valueOf(registrationRequest.getDateOfBirth()));
        employeeRegistrationRequest.setDateOfBirth(date);

        employeeRegistrationRequest.setIdPhoto(registrationRequest.getIdPhoto());

        // Save the employee details
        staffRegistrationRepository.save(employeeRegistrationRequest);

        // Mark the employee code as used and save it
        employeeCodeRequest.setUsed(true);
        employeeCodeRepository.save(employeeCodeRequest);

        // Return the response with the employee number
        return ResponseCodeMap.responseWithEmployeeNumber(ResponseCode.CREATED, employeeNumber);
    }

    }
