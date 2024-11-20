package com.example.listsHomework;

import com.example.listsHomework.Employee;
import exceptions.EmployeeAlreadyAddedException;
import exceptions.EmployeeNotFoundException;
import exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    private static final int maxEmployeesCount = 100;
    private final List<Employee> employees;

    public EmployeeService() {
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        if (employees.size() >= maxEmployeesCount) {
            throw new EmployeeStorageIsFullException("Невозможно добавить сотрудника, превышен лимит.");
        }
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже существует.");
        }
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        if (employees.contains(employee)) {
            employees.remove(employee);
        } else {
            throw new EmployeeNotFoundException("Такого сотрудника не существует.");
        }
    }

    public void findEmployee(Employee employee) {
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Такого сотрудника не существует.");
        }
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }
}

