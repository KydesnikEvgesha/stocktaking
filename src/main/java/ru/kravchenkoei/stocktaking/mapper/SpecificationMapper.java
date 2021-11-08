package ru.kravchenkoei.stocktaking.mapper;

import org.mapstruct.Mapper;
import ru.kravchenkoei.stocktaking.dto.SpecificationDto;
import ru.kravchenkoei.stocktaking.model.Specification;

@Mapper
public interface SpecificationMapper {
  SpecificationDto toDto(Specification specification);

  Specification toDomain(SpecificationDto specificationDto);
}
