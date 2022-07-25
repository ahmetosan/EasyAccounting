package com.easyaccounting.service.impl;

import com.easyaccounting.dto.ClientVendorDTO;
import com.easyaccounting.dto.InvoiceDTO;
import com.easyaccounting.entity.ClientVendor;
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

import java.time.LocalDate;
import java.util.Comparator;
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
        return listDTO.stream()
                .map(this::getPurchaseInvoiceCost)
                .map(this::getPurchaseInvoiceTax)
                .map(this::getPurchaseInvoiceTotalCost)
                .collect(Collectors.toList());
    }

    public InvoiceDTO getPurchaseInvoiceCost(InvoiceDTO purchaseInvoiceDTO){
        List<InvoiceProduct> listInvoiceProducts = invoiceProductRepository.findAllInvoiceProductsByInvoiceIdAndIsDeleted(purchaseInvoiceDTO.getId(), false);
        int costWithoutTax = 0;
        for (InvoiceProduct each:listInvoiceProducts) {
            costWithoutTax += each.getQty() * each.getPrice();
        }
        purchaseInvoiceDTO.setInvoiceCost(costWithoutTax);
    return purchaseInvoiceDTO;
    }

    public InvoiceDTO getPurchaseInvoiceTax(InvoiceDTO purchaseInvoiceDTO){
        List<InvoiceProduct> listInvoiceProducts = invoiceProductRepository.findAllInvoiceProductsByInvoiceIdAndIsDeleted(purchaseInvoiceDTO.getId(), false);
        int totalTax = 0;
        for (InvoiceProduct each:listInvoiceProducts) {
            totalTax += (each.getQty() * each.getPrice() * each.getTax())/100;
        }
        purchaseInvoiceDTO.setInvoiceTax(totalTax);
        return purchaseInvoiceDTO;
    }

    public InvoiceDTO getPurchaseInvoiceTotalCost(InvoiceDTO purchaseInvoiceDTO){
        List<InvoiceProduct> listInvoiceProducts = invoiceProductRepository.findAllInvoiceProductsByInvoiceIdAndIsDeleted(purchaseInvoiceDTO.getId(), false);
        int totalCostWithTax = 0;
        for (InvoiceProduct each:listInvoiceProducts) {
            totalCostWithTax += (each.getQty() * each.getPrice()) + (each.getQty() * each.getPrice() * each.getTax())/100;
        }
        purchaseInvoiceDTO.setTotalCost(totalCostWithTax);
        return purchaseInvoiceDTO;
    }


    @Override
    public InvoiceDTO findPurchaseInvoiceById(Long id) {
        Invoice invoice = purchaseInvoiceRepository.getById(id);
        return mapperUtil.convert(invoice, new InvoiceDTO());
    }

    @Override
    public InvoiceDTO createPurchaseInvoice(ClientVendorDTO clientVendorDTO) {
        ClientVendor clientVendor = mapperUtil.convert(clientVendorDTO, new ClientVendor());
        Invoice purchaseInvoice = new Invoice();
        purchaseInvoice.setEnabled(true);
        purchaseInvoice.setCompany(getCurrentCompany());
        purchaseInvoice.setInvoiceDate(LocalDate.now());
        purchaseInvoice.setInvoiceNumber(getInvoiceNumber());
        purchaseInvoice.setInvoiceType(InvoiceType.PURCHASE);
        purchaseInvoice.setInvoiceStatus(InvoiceStatus.PENDING);
        purchaseInvoice.setClientVendor(clientVendor);
        Invoice savedInvoice = purchaseInvoiceRepository.save(purchaseInvoice);
        return mapperUtil.convert(savedInvoice, new InvoiceDTO());
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

    @Override
    public InvoiceDTO calculateInvoiceCost(InvoiceDTO purchaseInvoiceDTO) {
        purchaseInvoiceDTO.setInvoiceCost(getPurchaseInvoiceCost(purchaseInvoiceDTO).getInvoiceCost());
        purchaseInvoiceDTO.setInvoiceTax(getPurchaseInvoiceTax(purchaseInvoiceDTO).getInvoiceTax());
        purchaseInvoiceDTO.setTotalCost(getPurchaseInvoiceTotalCost(purchaseInvoiceDTO).getTotalCost());
        return purchaseInvoiceDTO;
    }

    @Override
    public String getInvoiceNumber() {
        Company company = getCurrentCompany();
        List<Invoice> invoiceList = purchaseInvoiceRepository.findInvoicesByInvoiceTypeAndCompany(InvoiceType.PURCHASE, company)
                .stream()
                .sorted(Comparator.comparing(Invoice::getInvoiceNumber))
                .collect(Collectors.toList());
        int number = Integer.parseInt(invoiceList.get(invoiceList.size()-1).getInvoiceNumber().substring(5).replaceAll("[^0-9]", "")) + 1;
        return "P-INV-" + String.format("%03d", number);
    }

    public Company getCurrentCompany() {
// ToDO find current user, to return company info logged in by current user
        return companyRepository.findById(1L).get();
    }
}
