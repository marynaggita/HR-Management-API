package com.dfcu.staffRegistration.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class RegistrationRequest {
    @JsonProperty("employeeCode")  // Maps JSON property to this field
    private String employeeCode; // Employee code to check availability

    @JsonProperty("surname") // Maps JSON property to this field
    private String surname;

    @JsonProperty("otherName") // Maps JSON property to this field
    private String otherName;

    @JsonProperty("dateOfBirth") // Maps JSON property to this field
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // Ensures correct date format
    private Date dateOfBirth;

    @JsonProperty("idPhoto") // Maps JSON property to this field
    private String idPhoto;
}