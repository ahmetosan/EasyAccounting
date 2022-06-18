package com.easyaccounting.service;

import com.easyaccounting.dto.PurchaseInvoiceDTO;
import com.easyaccounting.enums.InvoiceType;

import java.util.List;

public interface PurchaseInvoiceService {

    List<PurchaseInvoiceDTO> listAllPurchaseInvoices(InvoiceType purchase);
    void approvePurchaseInvoice(Long id);
    PurchaseInvoiceDTO findPurchaseInvoiceById(Long id);
    void updatePurchaseInvoice(PurchaseInvoiceDTO invoiceDTO);
    void savePurchaseInvoice(PurchaseInvoiceDTO invoiceDTO);
    void deletePurchaseInvoiceById(Long id);
    void getToInvoiceById(Long id);
}
