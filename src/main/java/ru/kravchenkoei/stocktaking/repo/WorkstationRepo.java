package ru.kravchenkoei.stocktaking.repo;

import org.springframework.data.repository.CrudRepository;
import ru.kravchenkoei.stocktaking.model.Workstation;

public interface WorkstationRepo extends CrudRepository<Workstation, Long> {
}
