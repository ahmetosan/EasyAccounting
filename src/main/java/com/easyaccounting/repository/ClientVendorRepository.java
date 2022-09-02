package com.easyaccounting.repository;


import com.easyaccounting.entity.ClientVendor;
import com.easyaccounting.entity.Company;
import com.easyaccounting.enums.ClientVendorType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientVendorRepository extends JpaRepository<ClientVendor, Long> {

    List<ClientVendor> findAllByClientVendorTypeAndCompany(ClientVendorType type, Company company);

    List<ClientVendor> findAllByCompany(Company company);

    List<ClientVendor> findAllByCompanyAndClientVendorType(Company company, ClientVendorType companyType);

    List<ClientVendor> findAllByCompanyAndClientVendorTypeAndEnabledIsTrue(Company company, ClientVendorType companyType);

    List<ClientVendor> findAllByClientVendorTypeAndEnabledIsTrue(ClientVendorType companyType);

}