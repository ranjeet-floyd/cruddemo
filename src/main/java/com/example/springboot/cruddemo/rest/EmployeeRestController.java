package com.example.springboot.cruddemo.rest;

import com.example.springboot.cruddemo.entity.Employee;
import com.example.springboot.cruddemo.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Rest service for employee
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class EmployeeRestController {


    @Autowired
    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployee() {
        log.info("Get all employee");
        return employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee findById( @PathVariable("id") int id) {
        return employeeService.findById(id);
    }

    @PostMapping("/employees")
    public void create(@RequestBody Employee employee) {
        employeeService.save(employee);
    }

    @PutMapping("/employees")
    public void update(@RequestBody Employee employee) {
        employeeService.save(employee);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteById(@PathVariable("id") int id) {
        employeeService.deleteById(id);
    }
}
