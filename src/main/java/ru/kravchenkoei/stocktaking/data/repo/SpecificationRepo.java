package ru.kravchenkoei.stocktaking.data.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.kravchenkoei.stocktaking.data.model.Specification;

import java.util.Collection;

public interface SpecificationRepo extends CrudRepository<Specification, Long> {
  @Query("select s from Specification s where upper(s.name) like upper(concat('%', :filter, '%')) or upper(s.type.name) like upper(concat('%', :filter, '%')) order by s.name")
  Collection<Specification>
      findByNameLikeIgnoreCaseOrType_NameLikeIgnoreCaseAllIgnoreCaseOrderByNameAsc(
          String filter);
}