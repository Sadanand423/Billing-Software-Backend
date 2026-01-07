package com.billing.service.Impl;


import org.springframework.stereotype.Service;

import com.billing.entity.Employee;
import com.billing.repository.EmployeeRepository;
import com.billing.service.EmployeeService;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return repository.findById(id)
                .orElseThrow();
    }

    @Override
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}
