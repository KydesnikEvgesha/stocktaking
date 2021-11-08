package ru.kravchenkoei.stocktaking.mapper;

import org.mapstruct.Mapper;
import ru.kravchenkoei.stocktaking.dto.WorkstationDto;
import ru.kravchenkoei.stocktaking.model.Workstation;

@Mapper(uses = {ElementMapper.class})
public interface WorkstationMapper {
  WorkstationDto toDto(Workstation workstation);

  Workstation toDomain(WorkstationDto workstationDto);
}
