package com.dfcu.admin;

import com.dfcu.staffRegistration.models.EmployeeCodeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends JpaRepository<EmployeeCodeRequest, Long> {
    long count(); // To get total employee codes
}
