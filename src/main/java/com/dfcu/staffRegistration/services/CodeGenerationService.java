package com.dfcu.staffRegistration.services;

import com.dfcu.staffRegistration.models.EmployeeCodeRequest;
import com.dfcu.staffRegistration.repository.EmployeeCodeRepository;
import com.dfcu.staffRegistration.repository.StaffRegistrationRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;


@Service
public class CodeGenerationService {
    private final EmployeeCodeRepository idRepository;
    private static StaffRegistrationRepository staffRegistrationRepository;
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
    private static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final SecureRandom RANDOM = new SecureRandom();

    public CodeGenerationService(EmployeeCodeRepository idRepository, StaffRegistrationRepository staffRegistrationRepository) {
        this.idRepository = idRepository;
        this.staffRegistrationRepository = staffRegistrationRepository;
    }

    // Method to generate a random alphanumeric ID of the specified length
    public static String generateRandomId(int length) {
        StringBuilder id = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            id.append(ALPHANUMERIC.charAt(RANDOM.nextInt(ALPHANUMERIC.length())));
        }
        return id.toString();
    }

    public static String generateRandomId2(int length) {
        StringBuilder id = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            id.append(ALPHA.charAt(RANDOM.nextInt(ALPHA.length())));
        }
        return id.toString();
    }

    public String generateAndSaveEmployeeCode(int length) {
        String generatedCode = generateRandomId(length);

        // Create an instance of EmployeeCodeRequest and set the generated code
        EmployeeCodeRequest employeeCodeRequest = new EmployeeCodeRequest();
        employeeCodeRequest.setEmployeeCode(generatedCode);

        // Save the generated employee code in the database
        idRepository.save(employeeCodeRequest);

        // Return the generated employee code
        return generatedCode;
    }

    public String generateAndSaveEmployeeCode2(int length) {
        String generatedCode = generateRandomId2(length);

        // Create an instance of EmployeeCodeRequest and set the generated code
        EmployeeCodeRequest employeeCodeRequest = new EmployeeCodeRequest();
        employeeCodeRequest.setEmployeeCode(generatedCode);

        // Save the generated employee code in the database
        idRepository.save(employeeCodeRequest);

        // Return the generated employee code
        return generatedCode;
    }


    // Method to generate the next employee number based on the last used number
    static synchronized String generateNextEmployeeNumber(int lastUsedEmployeeNumber) {
        // Increment the last used employee number
        lastUsedEmployeeNumber++;
        // Generate the new employee number with leading zeros
        return "DFCU" + String.format("%03d", lastUsedEmployeeNumber);
    }
}
