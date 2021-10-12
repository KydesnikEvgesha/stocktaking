package ru.kravchenkoei.stocktaking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kravchenkoei.stocktaking.dto.LocationDto;
import ru.kravchenkoei.stocktaking.exception.EntityNotFoundException;
import ru.kravchenkoei.stocktaking.mapper.LocationMapper;
import ru.kravchenkoei.stocktaking.model.Location;
import ru.kravchenkoei.stocktaking.repo.LocationRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepo locationRepo;
    private final LocationMapper locationMapper;

    public LocationDto addLocation(LocationDto locationDto) {
        return Optional.of(locationDto)
                .map(locationMapper::toDomain)
                .map(locationRepo::save)
                .map(locationMapper::toDto)
                .get();
    }

    public List<LocationDto> findAllLocations() {
        ArrayList<Location> locationArrayList = (ArrayList<Location>) locationRepo.findAll();
        return locationArrayList.stream()
                .map(locationMapper::toDto)
                .collect(Collectors.toList());
    }

    public LocationDto updateLocation(LocationDto locationDto) {
        return addLocation(locationDto);
    }

    public LocationDto findLocationById(Long id) {
        return Optional.of(locationRepo.findById(id))
                .orElseThrow( () -> new EntityNotFoundException(
                        "Офис с уникальным номером " + id + " не был найден"
                ))
                .map(locationMapper::toDto)
                .get();
    }

    public void deleteLocation(Long id) {
        locationRepo.deleteById(id);
    }
}
