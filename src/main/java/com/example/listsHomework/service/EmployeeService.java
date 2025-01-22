package com.example.listsHomework.service;

import com.example.listsHomework.domain.Employee;
import com.example.listsHomework.exceptions.EmployeeAlreadyAddedException;
import com.example.listsHomework.exceptions.EmployeeNotFoundException;
import com.example.listsHomework.exceptions.InvalidInputException;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeService {

    private final Map<String, Employee> employees;

    public EmployeeService() {
        employees = new HashMap<>();
    }

    public Employee addEmployee(String firstName, String lastName, int salary, String department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        validateInput(firstName, lastName);

        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует.");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        validateInput(firstName, lastName);
        String key = getKey(firstName, lastName);
        if (employees.containsKey(key)) {
            return employees.remove(key);
        }
        throw new EmployeeNotFoundException("Сотрудник не найден.");
    }

    public Employee findEmployee(String firstName, String lastName) {
        validateInput(firstName, lastName);
        String key = getKey(firstName, lastName);
        if (employees.containsKey(key)) {
            return employees.get(key);
        }
        throw new EmployeeNotFoundException("Сотрудник не найден.");
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }

    public void addTestData() {
        employees.put("Иванов", new Employee("Иван", "Иванов", 20000, "1"));
        employees.put("Петров", new Employee("Петр", "Петров", 15000, "2"));
        employees.put("Сидоров", new Employee("Сидор", "Сидоров", 30000, "3"));
    }

    private void validateInput(String firstName, String lastName) {
        if (!(isAlpha(firstName) && isAlpha(lastName))) {
            throw new InvalidInputException();
        }
    }

    private String getKey(String firstName, String lastName) {
        return firstName + " " + lastName;
    }
}
