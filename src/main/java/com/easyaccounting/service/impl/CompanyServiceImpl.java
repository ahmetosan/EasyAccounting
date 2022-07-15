package com.easyaccounting.service.impl;


import com.easyaccounting.dto.CompanyDTO;
import com.easyaccounting.entity.Company;
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
    private final MapperUtil mapperUtil;

    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMapper companyMapper, MapperUtil mapperUtil) {
        this.companyRepository = companyRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<CompanyDTO> listAllCompanies() {

        Object company;
        return companyRepository.findAll().stream().map(ke ->
                mapperUtil.convert(ke, new CompanyDTO())).collect(Collectors.toList());
    }

    @Override
    public List<CompanyDTO> findAll() {
        return companyRepository.findAll().stream()
                .map(company -> mapperUtil.convert(company, new CompanyDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CompanyDTO> listAllActiveCompanies() {
        return null;
    }

    @Override
    public CompanyDTO findCompanyById(Long id) {
        Company company = companyRepository.findById(id).get();
        return mapperUtil.convert(company,new CompanyDTO());
    }

    @Override
    public void save(CompanyDTO companyDTO) {
        companyDTO.setEnabled(true);
        companyRepository.save(mapperUtil.convert(companyDTO, new Company()));
    }

    @Override
    public void update(CompanyDTO companyDTO) {
        companyRepository.save(mapperUtil.convert(companyDTO, new Company()));
    }

    @Override
    public void delete(CompanyDTO companyDTO) {

    }


    public Company getCurrentCompany() {
        return companyRepository.findById(1L).get();
    }

    @Override
    public CompanyDTO findByCompanyTitle(String title) {
        Company company = companyRepository.findCompanyByTitle(title);
        return mapperUtil.convert(company,new CompanyDTO());

    }

}
