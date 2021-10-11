package ru.kravchenkoei.stocktaking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kravchenkoei.stocktaking.exception.UserNotFoundException;
import ru.kravchenkoei.stocktaking.model.Employee;
import ru.kravchenkoei.stocktaking.repo.EmployeeRepo;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return (List<Employee>) employeeRepo.findAll();
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с уникальным номером " + id + " не был найден"));
    }

    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }

}
