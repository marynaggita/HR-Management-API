package com.dfcu.staffRegistration.repository;

import com.dfcu.staffRegistration.models.EmployeeCodeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeCodeRepository extends JpaRepository<EmployeeCodeRequest, String> {
    // Method to check if the employee code exists by code
    boolean existsByEmployeeCode(String employeeCode);

    // Method to find an employee code by the code
    EmployeeCodeRequest findByEmployeeCode(String employeeCode);

}
