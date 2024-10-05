package com.dfcu.staffRegistration.repository;

import com.dfcu.staffRegistration.models.EmployeeRegistrationRequest;
import com.dfcu.staffRetrieval.models.RetrievalRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRegistrationRepository extends JpaRepository<EmployeeRegistrationRequest, String> {
    boolean existsById(String id);

    // Query to fetch the last used employee number
    @Query("SELECT MAX(e.employeeNumber) FROM EmployeeRegistrationRequest e")
    static String findLastEmployeeNumber() {
        return null;
    }
    List<EmployeeRegistrationRequest> findByEmployeeNumber(String employeeNumber);

}
