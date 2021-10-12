package ru.kravchenkoei.stocktaking.repo;

import org.springframework.data.repository.CrudRepository;
import ru.kravchenkoei.stocktaking.model.Location;

public interface LocationRepo extends CrudRepository<Location, Long> {
}
