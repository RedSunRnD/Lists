package com.example.listsHomework.service;

import com.example.listsHomework.domain.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> getEmployeesByDepartment(String departmentID) {
        List<Employee> employees = employeeService.getAllEmployees();
        return employees.stream()
                .filter(e -> e.getDepartment().equals(departmentID))
                .collect(Collectors.toList());
    }

    public int getSalarySumByDepartment(String departmentID) {
        return employeeService.getAllEmployees().stream()
                .filter(employee -> employee.getDepartment().equals(departmentID))
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public Employee getEmployeeWithMaxSalaryByDepartment(String departmentID) {
        List<Employee> employees = employeeService.getAllEmployees();
        return employees.stream()
                .filter(e -> e.getDepartment().equals(departmentID))
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    public Employee getEmployeeWithMinSalaryByDepartment(String departmentID) {
        List<Employee> employees = employeeService.getAllEmployees();
        return employees.stream()
                .filter(e -> e.getDepartment().equals(departmentID))
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    public Map<Integer, List<Employee>> getAllEmployeesGroupedByDepartment() {
        return employeeService.getAllEmployees().stream()
                .collect(Collectors.groupingBy(employee -> Integer.parseInt(employee.getDepartment())));
    }
}
