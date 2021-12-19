package ru.kravchenkoei.stocktaking.data.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kravchenkoei.stocktaking.data.model.Employee;
import ru.kravchenkoei.stocktaking.data.repo.EmployeeRepo;
import ru.kravchenkoei.stocktaking.exception.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

  private final EmployeeRepo employeeRepo;

  public Employee addEmployee(Employee employeeDto) {
    return Optional.of(employeeDto)
            .map(employeeRepo::save)
            .get();
  }

  public List<Employee> findAllEmployees() {
    return (List<Employee>) employeeRepo.findAll();
  }

  public Employee updateEmployee(Employee employee) {
    return addEmployee(employee);
  }

  public Employee findEmployeeById(Long id) {
    return Optional.of(employeeRepo.findById(id))
            .orElseThrow( () -> new EntityNotFoundException(
                    "Пользователь с уникальным номером " + id + " не был найден"
            ))
            .get();
  }

  public void deleteEmployee(Long id) {
    employeeRepo.deleteById(id);
  }
}
