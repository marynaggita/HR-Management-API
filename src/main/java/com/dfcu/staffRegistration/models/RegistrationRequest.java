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
    @JsonProperty("employeeCode")
    private String employeeCode;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("otherName")
    private String otherName;

    @JsonProperty("dateOfBirth")

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @JsonProperty("idPhoto")
    private String idPhoto;
}