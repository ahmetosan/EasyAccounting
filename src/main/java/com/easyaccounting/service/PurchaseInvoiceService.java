package com.easyaccounting.service;

import com.easyaccounting.dto.PurchaseInvoiceDTO;
import com.easyaccounting.enums.InvoiceType;

import java.util.List;

public interface PurchaseInvoiceService {

    List<PurchaseInvoiceDTO> listAllPurchaseInvoices(InvoiceType purchase);
}
