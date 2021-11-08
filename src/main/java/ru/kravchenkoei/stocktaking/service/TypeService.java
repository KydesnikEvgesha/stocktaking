package ru.kravchenkoei.stocktaking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kravchenkoei.stocktaking.dto.TypeDto;
import ru.kravchenkoei.stocktaking.mapper.TypeMapper;
import ru.kravchenkoei.stocktaking.model.Type;
import ru.kravchenkoei.stocktaking.repo.TypeRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TypeService {
  private final TypeRepo typeRepo;
  private final TypeMapper typeMapper;

  public TypeDto addType(TypeDto typeDto) {
    return Optional.of(typeDto)
        .map(typeMapper::toDomain)
        .map(typeRepo::save)
        .map(typeMapper::toDto)
        .get();
  }

  public List<TypeDto> findAllTypes() {
    ArrayList<Type> typeArrayList = (ArrayList<Type>) typeRepo.findAll();
    return typeArrayList.stream().map(typeMapper::toDto).collect(Collectors.toList());
  }

  public TypeDto updateType(TypeDto typeDto) {
    return addType(typeDto);
  }

  public void deleteType(Long id) {
    typeRepo.deleteById(id);
  }
}
