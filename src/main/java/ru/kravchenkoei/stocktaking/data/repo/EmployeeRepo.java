package ru.kravchenkoei.stocktaking.data.repo;

import org.springframework.data.repository.CrudRepository;
import ru.kravchenkoei.stocktaking.data.model.Employee;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {
}
