package ru.kravchenkoei.stocktaking.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import ru.kravchenkoei.stocktaking.model.Company;

import java.util.List;

public interface CompanyRepo extends CrudRepository<Company, Long> {
  @Query("select c from Company c where upper(c.name) like upper(concat('%', :filter, '%')) or upper(c.address) like upper(concat('%', :filter, '%'))")
  List<Company> findByNameLikeIgnoreCaseOrAddressLikeIgnoreCase(
          @Param("filter") @Nullable String filter);

}
