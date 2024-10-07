package com.dfcu.staffUpdate.services;

import com.dfcu.authentication.AuthService;
import com.dfcu.staffRegistration.models.EmployeeRegistrationRequest;
import com.dfcu.staffRegistration.repository.StaffCustomRepository;
import com.dfcu.staffRegistration.repository.StaffRegistrationRepository;
import com.dfcu.staffUpdate.models.UpdateEmployeeRequest;

import com.dfcu.utils.ResponseCode;
import com.dfcu.utils.ResponseCodeMap;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateService {
    @Autowired
    private final StaffCustomRepository staffCustomRepository;
    private final AuthService authService;

    public UpdateService(StaffRegistrationRepository staffRegistrationRepository, StaffCustomRepository staffCustomRepository, AuthService authService) {
        this.staffCustomRepository = staffCustomRepository;
        this.authService = authService;
    }

    public EmployeeRegistrationRequest updateEmployee(String username,String password, String employeeNumber, String dateOfBirth, String idPhoto) {
        if (!authService.authenticate(username, password)) {
            throw new RuntimeException("Authentication failed: Invalid username or password");
        }
        EmployeeRegistrationRequest employee = staffCustomRepository.findByEmployeeNumber(employeeNumber);
        if (employee != null) {
            employee.setDateOfBirth(dateOfBirth);
            employee.setIdPhoto(idPhoto);
            return staffCustomRepository.save(employee);
        }
        return null;
    }
}
