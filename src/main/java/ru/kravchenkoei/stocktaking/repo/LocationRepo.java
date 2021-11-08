package ru.kravchenkoei.stocktaking.repo;

import org.springframework.data.repository.CrudRepository;
import ru.kravchenkoei.stocktaking.model.Location;

import java.util.List;

public interface LocationRepo extends CrudRepository<Location, Long> {

  List<Location> findDistinctByAddressOrderByAddress(String address);
}
