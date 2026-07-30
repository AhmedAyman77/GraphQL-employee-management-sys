package com.example.employee_management_system.services;

import com.example.employee_management_system.dtos.CreateDepartmentInput;
import com.example.employee_management_system.error.CustomResponseException;
import com.example.employee_management_system.models.Department;
import com.example.employee_management_system.repositories.DepartmentRepository;
import com.example.employee_management_system.services.interfaces.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepartmentServiceimp implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public Department findOne(UUID departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Department with id " + departmentId + " not found"
                ));

        return department;
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department createOne(CreateDepartmentInput departmentInput) {
        Department department = new Department();
        department.setName(departmentInput.name());

        departmentRepository.save(department);
        return department;
    }

    @Override
    public void deleteOne(UUID departmentId) {
        departmentRepository.deleteById(departmentId);
    }
}
