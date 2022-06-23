package com.easyaccounting.service.impl;


import com.easyaccounting.dto.CompanyDTO;
import com.easyaccounting.entity.Company;
import com.easyaccounting.entity.User;
import com.easyaccounting.mapper.CompanyMapper;
import com.easyaccounting.mapper.MapperUtil;
import com.easyaccounting.repository.CompanyRepository;
import com.easyaccounting.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final MapperUtil mapperUtil;

    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMapper companyMapper, MapperUtil mapperUtil) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<CompanyDTO> listAllCompanies() {

        Object company;
        return companyRepository.findAll().stream().map(ke ->
                mapperUtil.convert(ke, new CompanyDTO())).collect(Collectors.toList());
    }



    public Company getCurrentCompany() {

        return companyRepository.findById(1L).get();
    }

}
