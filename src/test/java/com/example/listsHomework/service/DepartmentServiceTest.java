package com.example.listsHomework.service;

import com.example.listsHomework.domain.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    private DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        departmentService = new DepartmentService(employeeService);
    }

    @Test
    void getEmployeesByDepartment() {
        Employee employee1 = new Employee("Иван", "Иванов", 20000, "1");
        Employee employee2 = new Employee("Петр", "Петров", 25000, "1");

        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employee1, employee2));

        List<Employee> employees = departmentService.getEmployeesByDepartment("1");

        assertEquals(2, employees.size());
        assertTrue(employees.contains(employee1));
        assertTrue(employees.contains(employee2));
    }

    @Test
    void getEmployeesByDepartmentWhenNoEmployeesInDepartment() {
        when(employeeService.getAllEmployees()).thenReturn(Collections.emptyList());

        List<Employee> employees = departmentService.getEmployeesByDepartment("1");

        assertTrue(employees.isEmpty());
    }

    @Test
    void getSalarySumByDepartment() {
        Employee employee1 = new Employee("Иван", "Иванов", 20000, "1");
        Employee employee2 = new Employee("Петр", "Петров", 25000, "1");

        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employee1, employee2));

        int salarySum = departmentService.getSalarySumByDepartment("1");

        assertEquals(45000, salarySum);
    }

    @Test
    void getEmployeeWithMaxSalaryByDepartment() {
        Employee employee1 = new Employee("Иван", "Иванов", 20000, "1");
        Employee employee2 = new Employee("Петр", "Петров", 25000, "1");

        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employee1, employee2));

        Employee maxSalaryEmployee = departmentService.getEmployeeWithMaxSalaryByDepartment("1");

        assertNotNull(maxSalaryEmployee);
        assertEquals(25000, maxSalaryEmployee.getSalary());
    }

    @Test
    void getEmployeeWithMinSalaryByDepartment() {
        Employee employee1 = new Employee("Иван", "Иванов", 20000, "1");
        Employee employee2 = new Employee("Петр", "Петров", 25000, "1");

        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employee1, employee2));

        Employee minSalaryEmployee = departmentService.getEmployeeWithMinSalaryByDepartment("1");

        assertNotNull(minSalaryEmployee);
        assertEquals(20000, minSalaryEmployee.getSalary());
    }

    @Test
    void getAllEmployeesGroupedByDepartment() {
        Employee employee1 = new Employee("Иван", "Иванов", 20000, "1");
        Employee employee2 = new Employee("Петр", "Петров", 25000, "2");

        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employee1, employee2));

        var groupedEmployees = departmentService.getAllEmployeesGroupedByDepartment();

        assertEquals(2, groupedEmployees.size());
        assertTrue(groupedEmployees.containsKey(1));
        assertTrue(groupedEmployees.containsKey(2));
    }
}
