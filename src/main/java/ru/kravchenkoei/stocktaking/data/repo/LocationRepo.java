package ru.kravchenkoei.stocktaking.data.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.kravchenkoei.stocktaking.data.model.Location;

import java.util.List;

public interface LocationRepo extends CrudRepository<Location, Long> {
  @Query(
      "select l from Location l where upper(l.nameOffice) like upper(concat('%', :filter, '%')) or upper(l.address) like upper(concat('%', :filter, '%')) order by l.nameOffice")
  List<Location>
      findByNameOfficeLikeIgnoreCaseOrAddressLikeIgnoreCaseAllIgnoreCaseOrderByNameOfficeAsc(
          String filter);
}
