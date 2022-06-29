package com.easyaccounting.service.impl;

import com.easyaccounting.dto.InvoiceDTO;
import com.easyaccounting.entity.Company;
import com.easyaccounting.entity.Invoice;
import com.easyaccounting.entity.InvoiceProduct;
import com.easyaccounting.enums.InvoiceStatus;
import com.easyaccounting.enums.InvoiceType;
import com.easyaccounting.mapper.MapperUtil;
import com.easyaccounting.mapper.PurchaseInvoiceMapper;
import com.easyaccounting.repository.CompanyRepository;
import com.easyaccounting.repository.InvoiceProductRepository;
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
    private final CompanyRepository companyRepository;
    private final InvoiceProductRepository invoiceProductRepository;

    public PurchaseInvoiceServiceImpl(PurchaseInvoiceRepository purchaseInvoiceRepository, MapperUtil mapperUtil, PurchaseInvoiceMapper purchaseInvoiceMapper, CompanyRepository companyRepository, InvoiceProductRepository invoiceProductRepository) {
        this.purchaseInvoiceRepository = purchaseInvoiceRepository;
        this.mapperUtil = mapperUtil;
        this.purchaseInvoiceMapper = purchaseInvoiceMapper;
        this.companyRepository = companyRepository;
        this.invoiceProductRepository = invoiceProductRepository;
    }

    @Override
    public List<InvoiceDTO> listAllPurchaseInvoices(InvoiceType type) {
        Company company = getCurrentCompany();
        List<Invoice> invoices = purchaseInvoiceRepository.findInvoicesByInvoiceTypeAndCompany(type, company);
        List<InvoiceDTO> listDTO = invoices.stream()
                .map(invoiceObj -> mapperUtil.convert(invoiceObj, new InvoiceDTO()))
                .collect(Collectors.toList());
        return listDTO;
    }

    public InvoiceDTO getPurchaseInvoiceCost(InvoiceDTO purchaseInvoiceDTO){
        List<InvoiceProduct> listInvoiceProducts = invoiceProductRepository.findAllByInvoiceId(purchaseInvoiceDTO.getId());
        // stream.map(each-> each.getCost)
    return purchaseInvoiceDTO;
    }

    @Override
    public InvoiceDTO findPurchaseInvoiceById(Long id) {
        Invoice invoice = purchaseInvoiceRepository.getById(id);
        return mapperUtil.convert(invoice, new InvoiceDTO());
    }

    @Override
    public void updatePurchaseInvoice(InvoiceDTO invoiceDTO) {
        Invoice purchaseInvoice = purchaseInvoiceRepository.findInvoiceById(invoiceDTO.getId());
        Invoice convertedPurchaseInvoice = purchaseInvoiceMapper.convertToEntity(invoiceDTO);
        convertedPurchaseInvoice.setId(purchaseInvoice.getId());
        purchaseInvoiceRepository.save(convertedPurchaseInvoice);
        findPurchaseInvoiceById(invoiceDTO.getId());
    }

    @Override
    public void savePurchaseInvoice(InvoiceDTO purchaseInvoiceDTO) {
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
        // need one more method for increase and decrease product qty
        purchaseInvoiceRepository.save(purchaseInvoice);
    }

    @Override
    public void getToInvoiceById(Long id) {
        Invoice purchaseInvoice = purchaseInvoiceRepository.findInvoiceById(id);
        purchaseInvoice.setInvoiceStatus(InvoiceStatus.APPROVED);
        purchaseInvoiceRepository.save(purchaseInvoice);
    }

    public Company getCurrentCompany() {
// ToDO find current user, to return company info logged in by current user
        return companyRepository.findById(1L).get();
    }
}
