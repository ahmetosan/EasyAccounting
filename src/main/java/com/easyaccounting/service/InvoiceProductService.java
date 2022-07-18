package com.easyaccounting.service;

import com.easyaccounting.dto.InvoiceProductDTO;
import com.easyaccounting.dto.ProductDTO;
import com.easyaccounting.entity.InvoiceProduct;

import java.util.List;

public interface InvoiceProductService {

    List<InvoiceProductDTO> getAllInvoiceProductsById(Long id);
    void updateInvoiceProduct(Long id);
    void saveInvoiceProduct(InvoiceProductDTO invoiceProductDTO);
    void deleteInvoiceProduct(Long id);
}
