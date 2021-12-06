package ru.kravchenkoei.stocktaking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kravchenkoei.stocktaking.dto.CompanyDto;
import ru.kravchenkoei.stocktaking.exception.EntityNotFoundException;
import ru.kravchenkoei.stocktaking.mapper.CompanyMapper;
import ru.kravchenkoei.stocktaking.model.Company;
import ru.kravchenkoei.stocktaking.repo.CompanyRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepo companyRepo;
    private final CompanyMapper companyMapper;

    public List<CompanyDto> findAllCompanies(){
        ArrayList<Company> companiesArrayList = (ArrayList<Company>) companyRepo.findAll();
        return companiesArrayList.stream().map(companyMapper::toDto).collect(Collectors.toList());
    }

    public CompanyDto saveCompany(CompanyDto companyDto) {
        return Optional.of(companyDto)
                .map(companyMapper::toDomain)
                .map(companyRepo::save)
                .map(companyMapper::toDto)
                .get();
    }

    public Company saveCompany(Company company) {
        return Optional.of(company)
                .map(companyRepo::save)
                .get();
    }

    public CompanyDto updateCompany(CompanyDto companyDto) {
        return saveCompany(companyDto);
    }

    public CompanyDto findCompanyById(Long id) {
        return Optional.of(companyRepo.findById(id))
                .orElseThrow(
                        () -> new EntityNotFoundException("Контрагент с уникальным номером " + id + " не был найден"))
                .map(companyMapper::toDto)
                .get();
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
