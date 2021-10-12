package ru.kravchenkoei.stocktaking.mapper;

import org.mapstruct.Mapper;
import ru.kravchenkoei.stocktaking.dto.EmployeeDto;
import ru.kravchenkoei.stocktaking.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDto toDto(Employee employee);
    Employee toDomain(EmployeeDto employeeDto);
}
