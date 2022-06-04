package com.easyaccounting.service;

import com.easyaccounting.dto.PurchaseInvoiceDTO;

import java.util.List;

public interface PurchaseInvoiceService {

    List<PurchaseInvoiceDTO> listAllPurchaseInvoices();
}
