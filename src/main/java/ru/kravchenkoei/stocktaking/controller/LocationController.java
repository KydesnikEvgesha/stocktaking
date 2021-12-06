package ru.kravchenkoei.stocktaking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kravchenkoei.stocktaking.dto.LocationDto;
import ru.kravchenkoei.stocktaking.service.LocationService;

import java.util.List;

@RestController
@RequestMapping("/api/location")
@CrossOrigin("*")
@RequiredArgsConstructor
public class LocationController {

  private final LocationService locationService;

  //@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @GetMapping(value = "/all")
  public ResponseEntity<List<LocationDto>> getAllLocations() {
    List<LocationDto> locations = locationService.findAllLocations();
    return new ResponseEntity<>(locations, HttpStatus.OK);
  }

  @GetMapping("/allbyaddress/{address}")
  public ResponseEntity<List<LocationDto>> getAllLocationsByAddress(
      @PathVariable("address") String address) {
    List<LocationDto> locations = locationService.findAllByAddressLocationDtos(address);
    return new ResponseEntity<>(locations, HttpStatus.OK);
  }

  @GetMapping("/find/{id}")
  public ResponseEntity<LocationDto> getLocation(@PathVariable("id") Long id) {
    LocationDto location = locationService.findLocationById(id);
    return new ResponseEntity<>(location, HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<LocationDto> addLocation(@RequestBody LocationDto locationDto) {
    LocationDto newLocationDto = locationService.addLocation(locationDto);
    return new ResponseEntity<>(newLocationDto, HttpStatus.OK);
  }

  @PutMapping("/update")
  public ResponseEntity<LocationDto> updateLocation(@RequestBody LocationDto locationDto) {
    LocationDto updateLocationDto = locationService.updateLocation(locationDto);
    return new ResponseEntity<>(updateLocationDto, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteLocation(@PathVariable("id") Long id) {
    locationService.deleteLocation(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
