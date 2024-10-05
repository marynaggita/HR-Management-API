package com.dfcu.staffUpdate.services;

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

    public UpdateService(StaffRegistrationRepository staffRegistrationRepository, StaffCustomRepository staffCustomRepository) {
        this.staffCustomRepository = staffCustomRepository;
    }

    public EmployeeRegistrationRequest updateEmployee(String employeeNumber, String dateOfBirth, String idPhoto) {
        EmployeeRegistrationRequest employee = staffCustomRepository.findByEmployeeNumber(employeeNumber);
        if (employee != null) {
            employee.setDateOfBirth(dateOfBirth);
            employee.setIdPhoto(idPhoto);
            return staffCustomRepository.save(employee);
        }
        return null;
    }
}
