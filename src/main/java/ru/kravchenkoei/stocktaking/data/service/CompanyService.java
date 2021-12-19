package ru.kravchenkoei.stocktaking.data.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kravchenkoei.stocktaking.data.model.Company;
import ru.kravchenkoei.stocktaking.data.repo.CompanyRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepo companyRepo;

    public List<Company> findAllCompanies(){
        return (List<Company>) companyRepo.findAll();
    }

    public Company saveCompany(Company company) {
        return Optional.of(company)
                .map(companyRepo::save)
                .get();
    }

    public Company updateCompany(Company company) {
        return saveCompany(company);
    }

    public void deleteCompany(Long id) {
        companyRepo.deleteById(id);
    }

    public List<Company> searchCompanies(String filter){
        if (filter == null || filter.isEmpty()){
            return (ArrayList<Company>) companyRepo.findAll();
        }else{
            return companyRepo.findByNameLikeIgnoreCaseOrAddressLikeIgnoreCase(filter);
        }
    }

}
