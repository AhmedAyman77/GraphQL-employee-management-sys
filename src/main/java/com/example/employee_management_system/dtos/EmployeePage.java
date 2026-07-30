package com.example.employee_management_system.dtos;

import com.example.employee_management_system.models.Employee;

import java.util.List;

public record EmployeePage(
        List<Employee> content,
        int currentPage,
        int totalPages,
        long totalItems,
        boolean hasNext,
        boolean hasPrevious
) {}
