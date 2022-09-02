package com.easyaccounting.service.impl;

import com.easyaccounting.dto.InvoiceDTO;
import com.easyaccounting.dto.InvoiceProductDTO;
import com.easyaccounting.entity.Invoice;
import com.easyaccounting.entity.InvoiceProduct;
import com.easyaccounting.mapper.MapperUtil;
import com.easyaccounting.repository.InvoiceProductRepository;
import com.easyaccounting.repository.PurchaseInvoiceRepository;
import com.easyaccounting.service.InvoiceProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceProductServiceImpl implements InvoiceProductService {

    private final InvoiceProductRepository invoiceProductRepository;
    private final MapperUtil mapperUtil;
    private final PurchaseInvoiceRepository purchaseInvoiceRepository;

    public InvoiceProductServiceImpl(InvoiceProductRepository invoiceProductRepository, MapperUtil mapperUtil, PurchaseInvoiceRepository purchaseInvoiceRepository) {
        this.invoiceProductRepository = invoiceProductRepository;
        this.mapperUtil = mapperUtil;
        this.purchaseInvoiceRepository = purchaseInvoiceRepository;
    }

    @Override
    public List<InvoiceProductDTO> getAllInvoiceProductsById(Long id) {
        List<InvoiceProduct> invoiceProductList = invoiceProductRepository.findAllInvoiceProductsByInvoiceIdAndIsDeleted(id, false);
        return invoiceProductList.stream()
                .map(obj -> mapperUtil.convert(obj, new InvoiceProductDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public void updateInvoiceProduct(Long id) {
//        List<InvoiceProduct> invoiceProducts = invoiceProductRepository.findAllByInvoiceId(id);

    }

    @Override
    public InvoiceProductDTO getInvoiceProductById(Long invoiceProductId) {
        InvoiceProduct invoiceProduct = invoiceProductRepository.findInvoiceProductById(invoiceProductId);
        return mapperUtil.convert(invoiceProduct, new InvoiceProductDTO());
    }

    @Override
    public void saveInvoiceProduct(InvoiceProductDTO invoiceProductDTO, InvoiceDTO invoiceDTO) {
        Invoice invoice = mapperUtil.convert(invoiceDTO, new Invoice());
        InvoiceProduct invoiceProduct = mapperUtil.convert(invoiceProductDTO, new InvoiceProduct());
        invoiceProduct.setInvoice(invoice);
        invoiceProduct.setId(null);
        invoiceProductRepository.save(invoiceProduct);
    }

    @Override
    public void deleteInvoiceProduct(Long invoiceProductId) {
        InvoiceProduct invoiceProduct = invoiceProductRepository.findById(invoiceProductId).get();
        invoiceProduct.setIsDeleted(true);
        invoiceProductRepository.save(invoiceProduct);
    }
}
