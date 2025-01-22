package com.example.listsHomework.service;

import com.example.listsHomework.domain.Employee;
import com.example.listsHomework.exceptions.EmployeeAlreadyAddedException;
import com.example.listsHomework.exceptions.EmployeeNotFoundException;
import com.example.listsHomework.exceptions.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService();
    }

    @Test
    void addEmployee() {
        Employee employee = employeeService.addEmployee("Иван", "Иванов", 20000, "1");
        assertNotNull(employee);
        assertEquals("Иван Иванов", employee.getFullName());
    }

    @Test
    void addEmployeeWhenEmployeeAlreadyExists() {
        employeeService.addEmployee("Иван", "Иванов", 20000, "1");

        assertThrows(EmployeeAlreadyAddedException.class, () ->
                employeeService.addEmployee("Иван", "Иванов", 20000, "1"));
    }

    @Test
    void removeEmployee() {
        employeeService.addEmployee("Иван", "Иванов", 20000, "1");
        Employee removedEmployee = employeeService.removeEmployee("Иван", "Иванов");

        assertNotNull(removedEmployee);
        assertEquals("Иван Иванов", removedEmployee.getFullName());
    }

    @Test
    void removeEmployeeWhenEmployeeNotFound() {
        assertThrows(EmployeeNotFoundException.class, () ->
                employeeService.removeEmployee("Неизвестный", "Сотрудник"));
    }

    @Test
    void findEmployee() {
        employeeService.addEmployee("Иван", "Иванов", 20000, "1");
        Employee foundEmployee = employeeService.findEmployee("Иван", "Иванов");

        assertNotNull(foundEmployee);
        assertEquals("Иван Иванов", foundEmployee.getFullName());
    }

    @Test
    void findEmployeeWhenEmployeeNotFound() {
        assertThrows(EmployeeNotFoundException.class, () ->
                employeeService.findEmployee("Неизвестный", "Сотрудник"));
    }

    @Test
    void addEmployeeWhenInvalidInput() {
        assertThrows(InvalidInputException.class, () ->
                employeeService.addEmployee("Иван", "Иванов123", 20000, "1"));
    }
}
