package ru.kravchenkoei.stocktaking.mapper;

import org.mapstruct.Mapper;
import ru.kravchenkoei.stocktaking.dto.TypeDto;
import ru.kravchenkoei.stocktaking.model.Type;

@Mapper
public interface TypeMapper {
  TypeDto toDto(Type type);

  Type toDomain(TypeDto typeDto);
}
