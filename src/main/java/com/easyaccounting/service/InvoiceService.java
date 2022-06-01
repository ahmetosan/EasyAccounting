package com.easyaccounting.service;

import com.easyaccounting.dto.InvoiceDTO;

import java.util.List;

public interface InvoiceService {
    List<InvoiceDTO> listAllPurchaseInvoices();
}
