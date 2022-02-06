package ru.kravchenkoei.stocktaking.data.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import ru.kravchenkoei.stocktaking.data.model.Employee;

import java.util.List;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {
  @Query("select e from Employee e where upper(e.firstName) like upper(concat('%', :filter, '%')) or upper(e.secondName) like upper(concat('%', :filter, '%')) or upper(e.email) like upper(concat('%', :filter, '%')) or upper(e.titleJob) like upper(concat('%', :filter, '%')) or upper(e.phone) like upper(concat('%', :filter, '%')) order by e.firstName, e.secondName")
  List<Employee> findByFieldIgnoreCaseAndOrderByFirstNameAndLastNameASC(@Param("filter") @Nullable String filter);
}
