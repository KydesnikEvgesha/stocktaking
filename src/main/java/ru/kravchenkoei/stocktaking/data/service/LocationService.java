package ru.kravchenkoei.stocktaking.data.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kravchenkoei.stocktaking.exception.EntityNotFoundException;
import ru.kravchenkoei.stocktaking.data.model.Location;
import ru.kravchenkoei.stocktaking.data.repo.LocationRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationService {

  private final LocationRepo locationRepo;

  public Location addLocation(Location location) {
    return Optional.of(location)
        .map(locationRepo::save)
        .get();
  }

  public List<Location> findAllLocations() {
    return (ArrayList<Location>) locationRepo.findAll();
  }

  public Location updateLocation(Location locationDto) {
    return addLocation(locationDto);
  }

  public Location findLocationById(Long id) {
    return Optional.of(locationRepo.findById(id))
        .orElseThrow(
            () -> new EntityNotFoundException("Офис с уникальным номером " + id + " не был найден"))
        .get();
  }

  public void deleteLocation(Long id) {
    locationRepo.deleteById(id);
  }
}
