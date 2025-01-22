package com.example.listsHomework.controller;

import com.example.listsHomework.domain.Employee;
import com.example.listsHomework.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesByDepartment(@PathVariable("id") String departmentID) {
        return departmentService.getEmployeesByDepartment(departmentID);
    }

    @GetMapping("/{id}/salary/sum")
    public int getSalarySumByDepartment(@PathVariable("id") String departmentID) {
        return departmentService.getSalarySumByDepartment(departmentID);
    }

    @GetMapping("/{id}/salary/max")
    public int getMaxSalaryByDepartment(@PathVariable("id") String departmentID) {
        return departmentService.getEmployeeWithMaxSalaryByDepartment(departmentID).getSalary();
    }

    @GetMapping("/{id}/salary/min")
    public int getMinSalaryByDepartment(@PathVariable("id") String departmentID) {
        return departmentService.getEmployeeWithMinSalaryByDepartment(departmentID).getSalary();
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getAllEmployeesGroupedByDepartment() {
        return departmentService.getAllEmployeesGroupedByDepartment();
    }
}
