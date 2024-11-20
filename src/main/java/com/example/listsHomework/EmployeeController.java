package com.example.listsHomework;

import exceptions.EmployeeAlreadyAddedException;
import exceptions.EmployeeNotFoundException;
import exceptions.EmployeeStorageIsFullException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    private EmployeeService employeeService = new EmployeeService();

    @GetMapping(path = "/employee/add")
    public String addEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Employee employee = new Employee(firstName, lastName);
        try {
            employeeService.addEmployee(employee);
        } catch (EmployeeStorageIsFullException e) {
            return "Ошибка добавления пользователя. " + e.getMessage();
        } catch (EmployeeAlreadyAddedException e) {
            return "Ошибка добавления пользователя. " + e.getMessage();
        } catch (RuntimeException e) {
            return "Ошибка добавления пользователя. " + e.getMessage();
        }
        return "Сотрудник добавлен.";
    }

    @GetMapping(path = "/employee/remove")
    public String removeEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Employee employee = new Employee(firstName, lastName);
        try {
            employeeService.removeEmployee(employee);
        } catch (EmployeeNotFoundException e) {
            return "Ошибка удаления. " + e.getMessage();
        } catch (RuntimeException e) {
            return "Ошибка удаления. " + e.getMessage();
        }
        return "Сотрудник удален.";
    }

    @GetMapping(path = "/employee/find")
    public String findEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Employee employee = new Employee(firstName, lastName);
        try {
            employeeService.findEmployee(employee);
            return "Сотрудник найден: " + employee;
        } catch (EmployeeNotFoundException e) {
            return "Ошибка: " + e.getMessage();
        } catch (RuntimeException e) {
            return "Ошибка: " + e.getMessage();
        }
    }

    @GetMapping(path = "/employee/list")
    public List<Employee> listEmployees() {
        return employeeService.getAllEmployees();
    }
}

