package ru.kravchenkoei.stocktaking.repo;

import org.springframework.data.repository.CrudRepository;
import ru.kravchenkoei.stocktaking.model.Employee;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {
}
