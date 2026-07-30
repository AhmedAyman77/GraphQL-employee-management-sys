package com.example.employee_management_system.repositories;

import com.example.employee_management_system.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {
}
