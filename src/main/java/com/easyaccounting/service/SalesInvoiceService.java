package com.easyaccounting.service;

import com.easyaccounting.dto.ClientVendorDTO;
import com.easyaccounting.dto.InvoiceDTO;
import com.easyaccounting.enums.InvoiceType;

import java.util.List;

public interface SalesInvoiceService {


    List<InvoiceDTO> listAllSalesInvoice(InvoiceType sales);
    void approveSalesInvoice(Long id);
    InvoiceDTO findSalesInvoiceById(Long id);
    void updateSalesInvoice(InvoiceDTO invoiceDTO);
    void deleteSalesInvoiceById(Long id);
    void getToInvoiceById(Long id);
    InvoiceDTO calculateInvoiceCost(InvoiceDTO purchaseInvoiceDTO);
    InvoiceDTO createSalesInvoice(ClientVendorDTO clientVendorDTO);
    String getInvoiceNumber();

}
