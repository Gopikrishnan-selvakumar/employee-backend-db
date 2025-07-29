package com.example.employee.controller;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    @GetMapping
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Employee create(@RequestBody Employee emp) {
        return repository.save(emp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        Employee emp = repository.findById(id).orElseThrow();
        return ResponseEntity.ok(emp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee data) {
        Employee emp = repository.findById(id).orElseThrow();
        emp.setFirstName(data.getFirstName());
        emp.setLastName(data.getLastName());
        emp.setEmail(data.getEmail());
        emp.setAddress(data.getAddress());
        return ResponseEntity.ok(repository.save(emp));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id) {
        Employee emp = repository.findById(id).orElseThrow();
        repository.delete(emp);
        Map<String, Boolean> resp = new HashMap<>();
        resp.put("deleted", true);
        return ResponseEntity.ok(resp);
    }
}
