package com.dfcu.staffRegistration.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tbl_employee_registration")
public class EmployeeRegistrationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String employeeNumber;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String otherName;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String dateOfBirth;

    @Column(nullable = true)
    private String idPhoto;

}
