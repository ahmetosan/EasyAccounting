package com.easyaccounting.repository;

import com.easyaccounting.entity.ClientVendor;
import com.easyaccounting.entity.Company;
import com.easyaccounting.enums.InvoiceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientVendorRepository extends JpaRepository<ClientVendor, Long> {

    List<ClientVendor> findAllByCompanyId(Long id);
}
