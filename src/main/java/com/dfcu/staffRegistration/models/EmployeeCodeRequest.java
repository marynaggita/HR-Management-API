package com.dfcu.staffRegistration.models;

import com.dfcu.staffRegistration.services.CodeGenerationService;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tbl_employee_codes")
public class EmployeeCodeRequest {
    @Id
    private String employeeCode;
    private LocalDate dateOfCreation;

    // Flag to indicate if the employee code has been used
    private boolean used;
}
