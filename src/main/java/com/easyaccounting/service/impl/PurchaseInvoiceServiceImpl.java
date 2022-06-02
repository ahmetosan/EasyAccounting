package com.easyaccounting.service.impl;

import com.easyaccounting.dto.PurchaseInvoiceDTO;
import com.easyaccounting.mapper.MapperUtil;
import com.easyaccounting.mapper.PurchaseInvoiceMapper;
import com.easyaccounting.service.PurchaseInvoiceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseInvoiceServiceImpl implements PurchaseInvoiceService {

    private final PurchaseInvoiceMapper purchaseInvoiceMapper;
    private final MapperUtil mapperUtil;

    public PurchaseInvoiceServiceImpl(PurchaseInvoiceMapper purchaseInvoiceMapper, MapperUtil mapperUtil) {
        this.purchaseInvoiceMapper = purchaseInvoiceMapper;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<PurchaseInvoiceDTO> listAllPurchaseInvoices() {
        return null;
    }
}
