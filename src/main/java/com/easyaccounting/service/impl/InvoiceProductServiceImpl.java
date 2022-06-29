package com.easyaccounting.service.impl;

import com.easyaccounting.dto.InvoiceProductDTO;
import com.easyaccounting.entity.InvoiceProduct;
import com.easyaccounting.mapper.MapperUtil;
import com.easyaccounting.repository.InvoiceProductRepository;
import com.easyaccounting.service.InvoiceProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceProductServiceImpl implements InvoiceProductService {

    private final InvoiceProductRepository invoiceProductRepository;
    private final MapperUtil mapperUtil;

    public InvoiceProductServiceImpl(InvoiceProductRepository invoiceProductRepository, MapperUtil mapperUtil) {
        this.invoiceProductRepository = invoiceProductRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<InvoiceProductDTO> getAllInvoiceProductsById(Long id) {
        List<InvoiceProduct> invoiceProductList = invoiceProductRepository.findAllByInvoiceId(id);
        return invoiceProductList.stream()
                .map(obj -> mapperUtil.convert(obj, new InvoiceProductDTO()))
                .collect(Collectors.toList());
    }
}
