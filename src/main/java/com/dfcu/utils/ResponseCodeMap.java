package com.dfcu.utils;

import com.dfcu.staffRegistration.models.RegistrationResponse;
import com.dfcu.staffRetrieval.models.RetrievalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseCodeMap {


    public static ResponseEntity<RegistrationResponse> response(ResponseCode responseCode) {
        return new ResponseEntity<>(
                new RegistrationResponse(responseCode.getHttpStatus().value(), responseCode.getMessage(), null),
                responseCode.getHttpStatus());
    }

    // Method to return a response with a custom message
    public static ResponseEntity<RegistrationResponse> response(ResponseCode responseCode, String customMessage) {
        return new ResponseEntity<>(
                new RegistrationResponse(responseCode.getHttpStatus().value(), customMessage, null),
                responseCode.getHttpStatus());
    }

    // New method to return a response with employee number
    public static ResponseEntity<RegistrationResponse> responseWithEmployeeNumber(ResponseCode responseCode, String employeeNumber) {
        return new ResponseEntity<>(
                new RegistrationResponse(responseCode.getHttpStatus().value(), responseCode.getMessage(), employeeNumber),
                responseCode.getHttpStatus());
    }

    public static <T> ResponseEntity<RetrievalResponse> retrievalResponse(ResponseCode responseCode, String customMessage, T data) {
        return  new ResponseEntity<>(
                new RetrievalResponse(responseCode.getHttpStatus().value(), customMessage,
                data),responseCode.getHttpStatus());

    }


}
