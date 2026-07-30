package com.example.employee_management_system.resolvers;

import com.example.employee_management_system.dtos.CreateEmployeeInput;
import com.example.employee_management_system.dtos.EmployeePage;
import com.example.employee_management_system.models.Employee;
import com.example.employee_management_system.services.interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Controller
@Validated
@RequiredArgsConstructor
public class EmployeeResolver {
    private final EmployeeService employeeService;

    @QueryMapping
    public EmployeePage getAllEmployees(@Argument int page, @Argument int size) {
        int zeroBasedPage = page - 1;
        Page<Employee> employees = employeeService.findAll(zeroBasedPage, size);

        return new EmployeePage(
                employees.getContent(),
                employees.getNumber(),
                employees.getTotalPages(),
                employees.getTotalElements(),
                employees.hasNext(),
                employees.hasPrevious()
        );
    }

    @QueryMapping
    public Employee getEmployee(@Argument String id) {
        return employeeService.findOne(UUID.fromString(id));
    }


    @MutationMapping
    public boolean deleteEmployee(@Argument String id) {
        employeeService.deleteOne(UUID.fromString(id));
        return true;
    }

    @MutationMapping
    public Employee createEmployee(@Argument UUID departmentId, @Argument("input") CreateEmployeeInput createEmployee) {
        return employeeService.createOne(departmentId, createEmployee);
    }
}
