package com.easyaccounting.service;

import com.easyaccounting.dto.ClientVendorDTO;
import com.easyaccounting.dto.InvoiceDTO;
import com.easyaccounting.enums.InvoiceType;

import java.util.List;

public interface PurchaseInvoiceService {

    List<InvoiceDTO> listAllPurchaseInvoices(InvoiceType purchase);
    void approvePurchaseInvoice(Long id);
    InvoiceDTO findPurchaseInvoiceById(Long id);
    InvoiceDTO createPurchaseInvoice(ClientVendorDTO clientVendorDTO);
    void deletePurchaseInvoiceById(Long id);
    void getToInvoiceById(Long id);
    InvoiceDTO calculateInvoiceCost(InvoiceDTO purchaseInvoiceDTO);
    String getInvoiceNumber();
}
