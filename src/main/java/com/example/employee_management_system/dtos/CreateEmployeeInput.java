package com.example.employee_management_system.dtos;

public record CreateEmployeeInput(
        String username,
        String email,
        String password
) {}
