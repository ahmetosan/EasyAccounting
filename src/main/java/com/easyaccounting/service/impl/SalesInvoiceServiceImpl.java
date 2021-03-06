package com.easyaccounting.service.impl;

import com.easyaccounting.dto.SalesInvoiceDTO;
import com.easyaccounting.entity.Invoice;
import com.easyaccounting.enums.InvoiceType;
import com.easyaccounting.mapper.MapperUtil;
import com.easyaccounting.repository.SalesInvoiceRepository;
import com.easyaccounting.service.SalesInvoiceService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesInvoiceServiceImpl implements SalesInvoiceService {

    private final SalesInvoiceRepository salesInvoiceRepository;
    private final MapperUtil mapperUtil;


    public SalesInvoiceServiceImpl(SalesInvoiceRepository salesInvoiceRepository, MapperUtil mapperUtil) {
        this.salesInvoiceRepository = salesInvoiceRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<SalesInvoiceDTO> listAllSalesInvoice(InvoiceType type) {

   List<Invoice> invoices = salesInvoiceRepository.findInvoiceByInvoiceType(type);

       return invoices.stream().map(salesInvoice -> mapperUtil.convert(salesInvoice, new SalesInvoiceDTO())).collect(Collectors.toList());
    }

}
