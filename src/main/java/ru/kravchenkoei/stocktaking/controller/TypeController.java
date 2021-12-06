package ru.kravchenkoei.stocktaking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kravchenkoei.stocktaking.dto.TypeDto;
import ru.kravchenkoei.stocktaking.service.TypeService;

import java.util.List;

@RestController
@RequestMapping("/api/type")
@CrossOrigin
@RequiredArgsConstructor
public class TypeController {
  private final TypeService typeService;

  @GetMapping("/all")
  public ResponseEntity<List<TypeDto>> getAllTypes() {
    List<TypeDto> typeDtoList = typeService.findAllTypes();
    return new ResponseEntity<>(typeDtoList, HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<TypeDto> addType(@RequestBody TypeDto typeDto) {
    TypeDto type = typeService.addType(typeDto);
    return new ResponseEntity<>(type, HttpStatus.CREATED);
  }

  @PutMapping("/update")
  public ResponseEntity<TypeDto> updateType(@RequestBody TypeDto typeDto) {
    TypeDto updateType = typeService.updateType(typeDto);
    return new ResponseEntity<>(updateType, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteType(@PathVariable("id") Long id) {
    typeService.deleteType(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
