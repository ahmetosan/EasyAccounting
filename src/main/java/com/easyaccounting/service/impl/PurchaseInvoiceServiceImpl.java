package com.easyaccounting.service.impl;

import com.easyaccounting.dto.PurchaseInvoiceDTO;
import com.easyaccounting.entity.Invoice;
import com.easyaccounting.enums.InvoiceType;
import com.easyaccounting.mapper.MapperUtil;
import com.easyaccounting.repository.PurchaseInvoiceRepository;
import com.easyaccounting.service.PurchaseInvoiceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseInvoiceServiceImpl implements PurchaseInvoiceService {

    private final PurchaseInvoiceRepository purchaseInvoiceRepository;
    private final MapperUtil mapperUtil;

    public PurchaseInvoiceServiceImpl(PurchaseInvoiceRepository purchaseInvoiceRepository, MapperUtil mapperUtil) {
        this.purchaseInvoiceRepository = purchaseInvoiceRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<PurchaseInvoiceDTO> listAllPurchaseInvoices(InvoiceType type) {

        List<Invoice> invoices = purchaseInvoiceRepository.findInvoicesByInvoiceType(type);
        return invoices.stream()
                .map(invoiceObj -> mapperUtil.convert(invoiceObj, new PurchaseInvoiceDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public void approvePurchaseInvoice(String invoiceNumber) {

    }

}
