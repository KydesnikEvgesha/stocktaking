package ru.kravchenkoei.stocktaking.data.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kravchenkoei.stocktaking.data.model.History;
import ru.kravchenkoei.stocktaking.data.repo.HistoryRepo;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepo historyRepo;

    public List<History> findAllHistory(){
        return (List<History>) historyRepo.findAll();
    }

    public History saveHistory(History history) {
        return Optional.of(history)
                .map(historyRepo::save)
                .get();
    }

    public History updateHistory(History history) {
        return saveHistory(history);
    }

    public void deleteHistory(Long id) {
        historyRepo.deleteById(id);
    }

}
