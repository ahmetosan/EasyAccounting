package com.easyaccounting.service.impl;

import com.easyaccounting.dto.ClientVendorDTO;
import com.easyaccounting.entity.ClientVendor;
import com.easyaccounting.entity.Company;
import com.easyaccounting.enums.InvoiceType;
import com.easyaccounting.mapper.ClientVendorMapper;
import com.easyaccounting.mapper.MapperUtil;
import com.easyaccounting.repository.ClientVendorRepository;
import com.easyaccounting.repository.CompanyRepository;
import com.easyaccounting.service.ClientVendorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientVendorServiceImpl implements ClientVendorService {
    private final ClientVendorRepository clientVendorRepository;
    private final MapperUtil mapperUtil;
    private final CompanyRepository companyRepository;

    public ClientVendorServiceImpl(ClientVendorRepository clientVendorRepository, MapperUtil mapperUtil, CompanyRepository companyRepository) {
        this.clientVendorRepository = clientVendorRepository;
        this.mapperUtil = mapperUtil;
        this.companyRepository = companyRepository;
    }


    @Override
    public List<ClientVendorDTO> getAllClientVendorByInvoiceType(InvoiceType type) {
        Company company = getCurrentCompany();
        List<ClientVendor> clientVendors = clientVendorRepository.findAllByCompanyId(company.getId());
        return clientVendors.stream()
                .map(obj-> obj.getType())
                .map(obj -> mapperUtil.convert(obj, new ClientVendorDTO()))
                .collect(Collectors.toList());
    }

    public Company getCurrentCompany() {
// ToDO find current user, to return company info logged in by current user
        return companyRepository.findById(1L).get();
    }
}
