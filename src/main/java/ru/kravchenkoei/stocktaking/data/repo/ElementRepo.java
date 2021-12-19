package ru.kravchenkoei.stocktaking.data.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import ru.kravchenkoei.stocktaking.data.model.Element;

import java.util.List;

public interface ElementRepo extends CrudRepository<Element, Long> {
    @Query("select c from Element c where upper(c.name) like upper(concat('%', :filter, '%'))")
    List<Element> findByNameLikeIgnoreCase(
            @Param("filter") @Nullable String filter);
}
