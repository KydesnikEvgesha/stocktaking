package ru.kravchenkoei.stocktaking.data.repo;

import org.springframework.data.repository.CrudRepository;
import ru.kravchenkoei.stocktaking.data.model.Location;

import java.util.List;

public interface LocationRepo extends CrudRepository<Location, Long> {

  List<Location> findDistinctByAddressOrderByAddress(String address);
}
