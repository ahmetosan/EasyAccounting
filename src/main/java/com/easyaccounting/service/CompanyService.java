package com.easyaccounting.service;


import com.easyaccounting.dto.CompanyDTO;
import com.easyaccounting.entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<CompanyDTO> listAllCompanies();
    List<CompanyDTO> findAll();
    List<CompanyDTO> listAllActiveCompanies();

    CompanyDTO findCompanyById(Long id);

    void save(CompanyDTO companyDTO);

    void update(CompanyDTO companyDTO);

    void delete(CompanyDTO companyDTO);


    CompanyDTO findByCompanyTitle(String title);
    //  List<CompanyDTO> getCompanies();

    Company getCurrentCompany();

}





