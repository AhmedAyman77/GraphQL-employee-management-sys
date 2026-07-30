package com.example.employee_management_system.services.interfaces;

import com.example.employee_management_system.dtos.CreateDepartmentInput;
import com.example.employee_management_system.models.Department;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {
    Department findOne(UUID departmentId);

    List<Department> findAll();

    Department createOne(CreateDepartmentInput departmentInput);

    void deleteOne(UUID departmentId);

}
