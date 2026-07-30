package com.example.employee_management_system.repositories;

import com.example.employee_management_system.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Optional<Employee> findOneByEmail(String email);
    Optional<Employee> findOneByUsername(String username);
}
