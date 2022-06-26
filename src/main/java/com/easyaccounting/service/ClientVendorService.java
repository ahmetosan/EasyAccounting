package com.easyaccounting.service;

import com.easyaccounting.dto.ClientVendorDTO;
import com.easyaccounting.entity.Company;
import com.easyaccounting.entity.Invoice;
import com.easyaccounting.enums.InvoiceType;

import java.util.List;

public interface ClientVendorService {

    List<ClientVendorDTO> getAllClientVendorByInvoiceType(InvoiceType type);
}
