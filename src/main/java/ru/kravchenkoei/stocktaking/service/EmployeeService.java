package ru.kravchenkoei.stocktaking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kravchenkoei.stocktaking.dto.EmployeeDto;
import ru.kravchenkoei.stocktaking.exception.EntityNotFoundException;
import ru.kravchenkoei.stocktaking.mapper.EmployeeMapper;
import ru.kravchenkoei.stocktaking.model.Employee;
import ru.kravchenkoei.stocktaking.repo.EmployeeRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

  private final EmployeeRepo employeeRepo;
  private final EmployeeMapper employeeMapper;

  public EmployeeDto addEmployee(EmployeeDto employeeDto) {
    return Optional.of(employeeDto)
            .map(employeeMapper::toDomain)
            .map(employeeRepo::save)
            .map(employeeMapper::toDto)
            .get();
  }

  public List<EmployeeDto> findAllEmployees() {
    ArrayList<Employee> employeeList = (ArrayList<Employee>) employeeRepo.findAll();
    return employeeList.stream()
            .map(employeeMapper::toDto)
            .collect(Collectors.toList());
  }

  public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
    return addEmployee(employeeDto);
  }

  public EmployeeDto findEmployeeById(Long id) {
    return Optional.of(employeeRepo.findById(id))
            .orElseThrow( () -> new EntityNotFoundException(
                    "Пользователь с уникальным номером " + id + " не был найден"
            ))
            .map(employeeMapper::toDto)
            .get();
  }

  public void deleteEmployee(Long id) {
    employeeRepo.deleteById(id);
  }
}
