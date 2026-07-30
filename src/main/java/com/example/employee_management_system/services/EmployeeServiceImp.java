package com.example.employee_management_system.services;

import com.example.employee_management_system.dtos.CreateEmployeeInput;
import com.example.employee_management_system.error.CustomResponseException;
import com.example.employee_management_system.models.Department;
import com.example.employee_management_system.models.Employee;
import com.example.employee_management_system.models.enums.EmployeeRole;
import com.example.employee_management_system.repositories.DepartmentRepository;
import com.example.employee_management_system.repositories.EmployeeRepository;
import com.example.employee_management_system.services.interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImp implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public Employee findOne(UUID employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Employee with id " + employeeId + " not found"
                ));

        return employee;
    }

    @Override
    public Page<Employee> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return employeeRepository.findAll(pageable);
    }

    @Override
    public void deleteOne(UUID employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        employee.ifPresent(value -> employeeRepository.deleteById(value.getId()));
    }

    @Override
    public Employee createOne(UUID departmentId, CreateEmployeeInput createEmployee) {

        employeeRepository.findOneByEmail(createEmployee.email())
                .ifPresent(e -> { throw CustomResponseException.BadRequest("email already exists"); });

        employeeRepository.findOneByUsername(createEmployee.username())
                .ifPresent(e -> { throw CustomResponseException.BadRequest("username already exists"); });

        System.out.println("--------------------------------------------------------------");

        Employee newEmployee = new Employee();
        newEmployee.setUsername(createEmployee.username());
        newEmployee.setEmail(createEmployee.email());
        newEmployee.setPassword(createEmployee.password());
        newEmployee.setRole(EmployeeRole.EMPLOYEE.name());

        Department userDepartment = departmentRepository.findById(departmentId).orElseThrow(
                () -> CustomResponseException.BadRequest("department not found")
        );
        newEmployee.setDepartment(userDepartment);

        return employeeRepository.save(newEmployee);
    }

}
