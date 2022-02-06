package ru.kravchenkoei.stocktaking.data.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kravchenkoei.stocktaking.data.model.SpecificationValue;
import ru.kravchenkoei.stocktaking.data.repo.SpecificationValueRepo;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpecificationValueService {
    private final SpecificationValueRepo specValRepo;

    public List<SpecificationValue> findAllSpecValue(){
        return (List<SpecificationValue>) specValRepo.findAll();
    }

    public SpecificationValue saveSpecValue(SpecificationValue specVal) {
        return Optional.of(specVal)
                .map(specValRepo::save)
                .get();
    }

    public SpecificationValue updateSpecValue(SpecificationValue specVal) {
        return saveSpecValue(specVal);
    }

    public void deleteSpecValue(Long id) {
        specValRepo.deleteById(id);
    }
}
