package com.dfcu.admin;

import com.dfcu.staffRegistration.models.EmployeeRegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeRegistrationRequest, Long> {
    long count(); // To get total employees
}
