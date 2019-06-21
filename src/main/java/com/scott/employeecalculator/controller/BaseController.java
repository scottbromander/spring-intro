package com.scott.employeecalculator.controller;

import com.scott.employeecalculator.data.EmployeeRepository;
import com.scott.employeecalculator.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class BaseController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value = "/add/employee", method = RequestMethod.POST)
    public @ResponseBody Employee addEmployee(@RequestBody Map<String, Object> payload) {
        String newId = String.valueOf(payload.get("id"));
        String newFName = String.valueOf(payload.get("firstName"));
        String newLName = String.valueOf(payload.get("lastName"));
        String newPosition = String.valueOf(payload.get("position"));
        String newSalary = String.valueOf(payload.get("salary"));

        Employee newEmployee = new Employee(newId, newFName, newLName, newPosition, newSalary);
        employeeRepository.addEmployee(newEmployee);

        return newEmployee;
    }

    @RequestMapping("/get/employees")
    public @ResponseBody List<Employee> getEmployees() {
        return employeeRepository.getAllEmployees();
    }

    @RequestMapping("/search/{id}")
    public @ResponseBody List<Employee> searchById(@PathVariable String id) {
        return employeeRepository.searchById(id);
    }

    @RequestMapping("/")
    public String baseRoute() {
        return "index";
    }
}
