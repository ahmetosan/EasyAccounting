package com.easyaccounting.service;


import com.easyaccounting.dto.ClientVendorDTO;
import com.easyaccounting.enums.ClientVendorType;

import java.util.List;

public interface ClientVendorService {

    List<ClientVendorDTO> getAllClientVendorsByCompanyType(ClientVendorType companyType);

    List<ClientVendorDTO> listAllClientVendors();

    List<ClientVendorDTO> listAllActiveClients();

    List<ClientVendorDTO> listAllActiveVendors();

    ClientVendorDTO findClientVendorById(Long id);

    void save(ClientVendorDTO clientVendorDto);

    void update(ClientVendorDTO clientVendorDto);

    void delete(Long id);
}

