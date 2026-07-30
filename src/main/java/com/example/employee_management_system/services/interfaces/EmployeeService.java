package com.example.employee_management_system.services.interfaces;

import com.example.employee_management_system.dtos.CreateEmployeeInput;
import com.example.employee_management_system.models.Employee;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface EmployeeService {
    Employee findOne(UUID employeeId);

    Page<Employee> findAll(int page, int size);

    void deleteOne(UUID employeeId);

    Employee createOne(UUID departmentId, CreateEmployeeInput createEmployee);
}
