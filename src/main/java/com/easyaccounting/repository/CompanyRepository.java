package com.easyaccounting.repository;


import com.easyaccounting.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long>{
    List<Company> findAll();

    Company findCompanyByTitle(String title);

}


