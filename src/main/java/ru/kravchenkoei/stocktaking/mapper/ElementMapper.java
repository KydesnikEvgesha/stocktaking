package ru.kravchenkoei.stocktaking.mapper;

import org.mapstruct.Mapper;
import ru.kravchenkoei.stocktaking.dto.ElementDto;
import ru.kravchenkoei.stocktaking.model.Element;

@Mapper(uses = {SpecificationMapper.class})
public interface ElementMapper {
  ElementDto toDto(Element element);

  Element toDomain(ElementDto elementDto);
}
