package com.dfcu.utils;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseCode {
    SUCCESS(HttpStatus.OK, "Operation completed successfully"),
    CREATED(HttpStatus.CREATED, "Employee registered successfully"),

    CONFLICT(HttpStatus.CONFLICT, "Conflict occurred"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "Employee Code Not Found"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST,"Employee code must not be null");

    // Return the entire HttpStatus object if needed
    private final HttpStatus httpStatus;
    private final String message;

    ResponseCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }



}
