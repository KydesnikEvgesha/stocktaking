package ru.kravchenkoei.stocktaking.data.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kravchenkoei.stocktaking.data.model.Type;
import ru.kravchenkoei.stocktaking.data.repo.TypeRepo;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TypeService {
  private final TypeRepo typeRepo;

  public Type saveType(Type type) {
    return Optional.of(type)
            .map(typeRepo::save)
            .get();
  }

  public void deleteType(Long id) {
    typeRepo.deleteById(id);
  }

  public List<Type> searchTypes(String filter){
    if (filter == null || filter.isEmpty()){
      return (List<Type>) typeRepo.findAll();
    }else{
      return typeRepo.findByNameLikeIgnoreCase(filter);
    }
  }
}
