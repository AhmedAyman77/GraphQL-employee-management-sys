package com.example.employee_management_system.dtos;

public record CreateEmployee(
        String username,
        String email,
        String password
) {}
