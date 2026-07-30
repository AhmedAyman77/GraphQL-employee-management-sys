package com.example.employee_management_system.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateDepartmentInput(
        @NotNull(message = "name is required")
        @Size(min = 2, max = 50, message = "min is 2 characters and max is 50 characters")
        String name
) {}
