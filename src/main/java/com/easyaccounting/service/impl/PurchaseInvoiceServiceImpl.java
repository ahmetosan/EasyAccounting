package com.easyaccounting.service.impl;

import com.easyaccounting.dto.PurchaseInvoiceDTO;
import com.easyaccounting.entity.Invoice;
import com.easyaccounting.enums.InvoiceStatus;
import com.easyaccounting.enums.InvoiceType;
import com.easyaccounting.mapper.MapperUtil;
import com.easyaccounting.mapper.PurchaseInvoiceMapper;
import com.easyaccounting.repository.PurchaseInvoiceRepository;
import com.easyaccounting.service.PurchaseInvoiceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseInvoiceServiceImpl implements PurchaseInvoiceService {

    private final PurchaseInvoiceRepository purchaseInvoiceRepository;
    private final MapperUtil mapperUtil;
    private final PurchaseInvoiceMapper purchaseInvoiceMapper;

    public PurchaseInvoiceServiceImpl(PurchaseInvoiceRepository purchaseInvoiceRepository, MapperUtil mapperUtil, PurchaseInvoiceMapper purchaseInvoiceMapper) {
        this.purchaseInvoiceRepository = purchaseInvoiceRepository;
        this.mapperUtil = mapperUtil;
        this.purchaseInvoiceMapper = purchaseInvoiceMapper;
    }

    @Override
    public List<PurchaseInvoiceDTO> listAllPurchaseInvoices(InvoiceType type) {
        List<Invoice> invoices = purchaseInvoiceRepository.findInvoicesByInvoiceType(type);
        return invoices.stream()
                .map(invoiceObj -> mapperUtil.convert(invoiceObj, new PurchaseInvoiceDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public PurchaseInvoiceDTO findPurchaseInvoiceById(Long id) {
        Invoice invoice = purchaseInvoiceRepository.getById(id);
        return mapperUtil.convert(invoice, new PurchaseInvoiceDTO());
    }

    @Override
    public void updatePurchaseInvoice(PurchaseInvoiceDTO invoiceDTO) {
        Invoice purchaseInvoice = purchaseInvoiceRepository.findInvoiceById(invoiceDTO.getId());
        Invoice convertedPurchaseInvoice = purchaseInvoiceMapper.convertToEntity(invoiceDTO);
        convertedPurchaseInvoice.setId(purchaseInvoice.getId());
        purchaseInvoiceRepository.save(convertedPurchaseInvoice);
        findPurchaseInvoiceById(invoiceDTO.getId());
    }

    @Override
    public void savePurchaseInvoice(PurchaseInvoiceDTO purchaseInvoiceDTO) {

        purchaseInvoiceDTO.setInvoiceStatus(String.valueOf(InvoiceStatus.PENDING));
        Invoice purchaseInvoice = purchaseInvoiceMapper.convertToEntity(purchaseInvoiceDTO);
        purchaseInvoiceRepository.save(purchaseInvoice);

    }

    @Override
    public void deletePurchaseInvoiceById(Long id) {
        Invoice purchaseInvoice = purchaseInvoiceRepository.findInvoiceById(id);
        purchaseInvoice.setIsDeleted(true);
        purchaseInvoice.setInvoiceNumber(purchaseInvoice.getInvoiceNumber() + "-" + purchaseInvoice.getId());
        purchaseInvoiceRepository.save(purchaseInvoice);
    }

    @Override
    public void approvePurchaseInvoice(Long id) {
        Invoice purchaseInvoice = purchaseInvoiceRepository.findInvoiceById(id);
        purchaseInvoice.setInvoiceStatus(InvoiceStatus.APPROVED);
        purchaseInvoiceRepository.save(purchaseInvoice);
    }

    @Override
    public void getToInvoiceById(Long id) {
        Invoice purchaseInvoice = purchaseInvoiceRepository.findInvoiceById(id);
        purchaseInvoice.setInvoiceStatus(InvoiceStatus.APPROVED);
        purchaseInvoiceRepository.save(purchaseInvoice);
    }

}
