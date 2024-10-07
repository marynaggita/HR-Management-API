package com.dfcu.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeCodeService {


    private final CodeRepository employeeCodeRepository;

    @Autowired
    public EmployeeCodeService(CodeRepository  employeeCodeRepository) {
        this.employeeCodeRepository = employeeCodeRepository;
    }

    public long getTotalEmployeeCodesGenerated() {
        return employeeCodeRepository.count();
    }


}