package ru.kravchenkoei.stocktaking.data.repo;

import org.springframework.data.repository.CrudRepository;
import ru.kravchenkoei.stocktaking.data.model.Workstation;

public interface WorkstationRepo extends CrudRepository<Workstation, Long> {
}
