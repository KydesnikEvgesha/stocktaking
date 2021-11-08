package ru.kravchenkoei.stocktaking.mapper;

import org.mapstruct.Mapper;
import ru.kravchenkoei.stocktaking.dto.CompanyDto;
import ru.kravchenkoei.stocktaking.model.Company;

@Mapper
public interface CompanyMapper {
  CompanyDto toDto(Company company);

  Company toDomain(CompanyDto companyDto);
}
