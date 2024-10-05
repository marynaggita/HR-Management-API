package com.dfcu.staffRegistration.repository;

import com.dfcu.staffRegistration.models.EmployeeRegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffCustomRepository extends JpaRepository<EmployeeRegistrationRequest, String> {

    EmployeeRegistrationRequest findByEmployeeNumber(String employeeNumber);
}
