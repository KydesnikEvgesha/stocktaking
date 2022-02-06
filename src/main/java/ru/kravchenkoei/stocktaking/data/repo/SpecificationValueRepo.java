package ru.kravchenkoei.stocktaking.data.repo;

import org.springframework.data.repository.CrudRepository;
import ru.kravchenkoei.stocktaking.data.model.SpecificationValue;

public interface SpecificationValueRepo extends CrudRepository<SpecificationValue, Long> {
}