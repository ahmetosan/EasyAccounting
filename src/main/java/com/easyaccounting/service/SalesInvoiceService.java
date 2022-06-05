package com.easyaccounting.service;

import com.easyaccounting.dto.SalesInvoiceDTO;
import com.easyaccounting.enums.InvoiceType;

import java.util.List;

public interface SalesInvoiceService {


    List<SalesInvoiceDTO> listAllSalesInvoice(InvoiceType sales);

}
