package com.easyaccounting.service;

import com.easyaccounting.dto.InvoiceDTO;
import com.easyaccounting.enums.InvoiceType;

import java.util.List;

public interface PurchaseInvoiceService {

    List<InvoiceDTO> listAllPurchaseInvoices(InvoiceType purchase);
    void approvePurchaseInvoice(Long id);
    InvoiceDTO findPurchaseInvoiceById(Long id);
    InvoiceDTO updatePurchaseInvoice(InvoiceDTO invoiceDTO, Long id);
    void savePurchaseInvoice(InvoiceDTO invoiceDTO);
    void deletePurchaseInvoiceById(Long id);
    void getToInvoiceById(Long id);
}
