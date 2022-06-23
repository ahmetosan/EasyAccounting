package com.easyaccounting.service;

import com.easyaccounting.dto.InvoiceProductDTO;

import java.util.List;

public interface InvoiceProductService {

    List<InvoiceProductDTO> getAllInvoiceProductsById(Long id);
}
