package ru.kravchenkoei.stocktaking.data.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kravchenkoei.stocktaking.data.model.Element;
import ru.kravchenkoei.stocktaking.data.repo.ElementRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ElementService {
    private final ElementRepo elementRepo;

    public List<Element> findAllCompanies(){
        return (ArrayList<Element>) elementRepo.findAll();
    }

    public Element saveElement(Element element) {
        return Optional.of(element)
                .map(elementRepo::save)
                .get();
    }

    public void deleteElement(Long id) {
        elementRepo.deleteById(id);
    }

    public List<Element> searchElements(String filter){
        if (filter == null || filter.isEmpty()){
            return (ArrayList<Element>) elementRepo.findAll();
        }else{
            return elementRepo.findByNameLikeIgnoreCase(filter);
        }
    }
}
