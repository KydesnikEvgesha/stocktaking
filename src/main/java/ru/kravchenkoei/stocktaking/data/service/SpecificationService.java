package ru.kravchenkoei.stocktaking.data.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kravchenkoei.stocktaking.data.model.Specification;
import ru.kravchenkoei.stocktaking.data.repo.SpecificationRepo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpecificationService {
    private final SpecificationRepo specRepo;

    public List<Specification> findAllSpecifications(){
        return (List<Specification>) specRepo.findAll();
    }

    public Specification saveSpecification(Specification specification) {
        return Optional.of(specification)
                .map(specRepo::save)
                .get();
    }

    public Specification updateSpecification(Specification specification) {
        return saveSpecification(specification);
    }

    public void deleteSpecification(Long id) {
        specRepo.deleteById(id);
    }

    public Collection<Specification> searchSpecifications(String filter) {
        if (filter == null || filter.isEmpty()){
            return (ArrayList<Specification>) specRepo.findAll();
        }else{
            return specRepo.findByNameLikeIgnoreCaseOrType_NameLikeIgnoreCaseAllIgnoreCaseOrderByNameAsc(filter);
        }
    }
}
