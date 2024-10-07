package com.dfcu.staffRegistration.models;

import com.dfcu.staffRegistration.services.CodeGenerationService;
import lombok.*;

@Getter
@Setter
@ToString

@NoArgsConstructor
public class RegistrationResponse {
    private int statusCode;
    private String message;
    private String employeeNumber;

    public RegistrationResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;

    }

    public RegistrationResponse(int code, String message, String employeeNumber) {
        this.statusCode = code;
        this.message = message;
        this.employeeNumber = employeeNumber;
    }



}
