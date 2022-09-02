package com.easyaccounting.service.impl;


import com.easyaccounting.dto.ClientVendorDTO;
import com.easyaccounting.entity.ClientVendor;
import com.easyaccounting.entity.common.UserPrincipal;
import com.easyaccounting.enums.ClientVendorType;
import com.easyaccounting.mapper.MapperUtil;
import com.easyaccounting.entity.Company;
import com.easyaccounting.repository.CompanyRepository;
import com.easyaccounting.repository.ClientVendorRepository;
import com.easyaccounting.service.ClientVendorService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<ClientVendorDTO> getAllClientVendorsByCompanyType(ClientVendorType type) {
        Company company = getCurrentCompany();
        List<ClientVendor> list = clientVendorRepository.findAllByClientVendorTypeAndCompany(type,company);
        List<ClientVendorDTO> listDto = list.stream()
                .map(each -> mapperUtil.convert(each, new ClientVendorDTO()))
                .collect(Collectors.toList());
        return listDto;
    }

    public Company getCurrentCompany() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return companyRepository.findById(userPrincipal.getLoggedInUserCompanyId()).get();
    }

    @Override
    public List<ClientVendorDTO> listAllClientVendors() {
        return clientVendorRepository.findAll().stream()
                .map(clientVendor -> mapperUtil.convert(clientVendor, new ClientVendorDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientVendorDTO> listAllActiveClients() {
        return clientVendorRepository.findAllByClientVendorTypeAndEnabledIsTrue(ClientVendorType.CLIENT).stream()
                .map(clientVendor -> mapperUtil.convert(clientVendor, new ClientVendorDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientVendorDTO> listAllActiveVendors() {
        return clientVendorRepository.findAllByClientVendorTypeAndEnabledIsTrue(ClientVendorType.VENDOR).stream()
                .map(clientVendor -> mapperUtil.convert(clientVendor, new ClientVendorDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public ClientVendorDTO findClientVendorById(Long id) {
        return mapperUtil.convert(clientVendorRepository.getById(id), new ClientVendorDTO());
    }

    @Override
    public void save(ClientVendorDTO clientVendorDto) {
        clientVendorDto.setEnabled(true);
        clientVendorRepository.save(mapperUtil.convert(clientVendorDto, new ClientVendor()));
    }

    @Override
    public void update(ClientVendorDTO clientVendorDto) {
        clientVendorRepository.save(mapperUtil.convert(clientVendorDto, new ClientVendor()));
    }
//
//     todo: May be invoices related to this client vendor should be also deleted
    @Override
    public void delete(Long id) {
        ClientVendor clientVendor = clientVendorRepository.getById(id);
        clientVendor.setIsDeleted(true);
        clientVendorRepository.save(clientVendor);
    }
}
