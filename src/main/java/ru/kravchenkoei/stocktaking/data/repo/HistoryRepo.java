package ru.kravchenkoei.stocktaking.data.repo;

import org.springframework.data.repository.CrudRepository;
import ru.kravchenkoei.stocktaking.data.model.History;

public interface HistoryRepo extends CrudRepository<History, Long> {
}