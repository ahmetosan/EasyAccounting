package com.easyaccounting.service;

import com.easyaccounting.dto.InvoiceDTO;
import com.easyaccounting.enums.InvoiceType;

import java.util.List;

public interface SalesInvoiceService {


    List<InvoiceDTO> listAllSalesInvoice(InvoiceType sales);
    void approveSalesInvoice(String invoiceNumber);

}
