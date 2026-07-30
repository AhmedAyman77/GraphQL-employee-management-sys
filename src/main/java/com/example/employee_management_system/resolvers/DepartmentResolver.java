package com.example.employee_management_system.resolvers;

import com.example.employee_management_system.dtos.CreateDepartmentInput;
import com.example.employee_management_system.models.Department;
import com.example.employee_management_system.services.interfaces.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Controller
@Validated
@RequiredArgsConstructor
public class DepartmentResolver {
    private final DepartmentService departmentService;

    @QueryMapping
    public List<Department> getAllDepartments() {
        return departmentService.findAll();
    }

    @QueryMapping
    public Department getDepartment(@Argument String id) {
        return departmentService.findOne(UUID.fromString(id));
    }

    @MutationMapping
    public Department createDepartment(@Argument @Valid CreateDepartmentInput input) {
        return departmentService.createOne(input);
    }

    @MutationMapping
    public boolean deleteDepartment(@Argument String id) {
        departmentService.deleteOne(UUID.fromString(id));
        return true;
    }
}
