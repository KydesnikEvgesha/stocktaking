package ru.kravchenkoei.stocktaking.repo;

import org.springframework.data.repository.CrudRepository;
import ru.kravchenkoei.stocktaking.model.Type;

public interface TypeRepo extends CrudRepository<Type, Long> {}
