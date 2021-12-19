package ru.kravchenkoei.stocktaking.data.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import ru.kravchenkoei.stocktaking.data.model.Element;
import ru.kravchenkoei.stocktaking.data.model.Type;

import java.util.List;

public interface TypeRepo extends CrudRepository<Type, Long> {

    @Query("select c from Type c where upper(c.name) like upper(concat('%', :filter, '%'))")
    List<Type> findByNameLikeIgnoreCase(
            @Param("filter") @Nullable String filter);
}
