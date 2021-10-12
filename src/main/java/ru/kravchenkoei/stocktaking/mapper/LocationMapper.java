package ru.kravchenkoei.stocktaking.mapper;

import org.mapstruct.Mapper;
import ru.kravchenkoei.stocktaking.dto.LocationDto;
import ru.kravchenkoei.stocktaking.model.Location;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationDto toDto(Location location);
    Location toDomain(LocationDto locationDto);
}
