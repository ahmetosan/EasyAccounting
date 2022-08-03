package com.easyaccounting.service.impl;

import com.easyaccounting.dto.ClientVendorDTO;
import com.easyaccounting.dto.InvoiceDTO;
import com.easyaccounting.entity.ClientVendor;
import com.easyaccounting.entity.Company;
import com.easyaccounting.entity.Invoice;
import com.easyaccounting.entity.InvoiceProduct;
import com.easyaccounting.entity.common.UserPrincipal;
import com.easyaccounting.enums.InvoiceStatus;
import com.easyaccounting.enums.InvoiceType;
import com.easyaccounting.mapper.MapperUtil;
import com.easyaccounting.mapper.SalesInvoiceMapper;
import com.easyaccounting.repository.CompanyRepository;
import com.easyaccounting.repository.InvoiceProductRepository;
import com.easyaccounting.repository.SalesInvoiceRepository;
import com.easyaccounting.service.SalesInvoiceService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesInvoiceServiceImpl implements SalesInvoiceService {

    private final SalesInvoiceRepository salesInvoiceRepository;
    private final MapperUtil mapperUtil;
    private final SalesInvoiceMapper salesInvoiceMapper;
    private final CompanyRepository companyRepository;
    private final InvoiceProductRepository invoiceProductRepository;
    private UserPrincipal userPrincipal;

    public SalesInvoiceServiceImpl(SalesInvoiceRepository salesInvoiceRepository, MapperUtil mapperUtil, SalesInvoiceMapper salesInvoiceMapper, CompanyRepository companyRepository, InvoiceProductRepository invoiceProductRepository) {
        this.salesInvoiceRepository = salesInvoiceRepository;
        this.mapperUtil = mapperUtil;
        this.salesInvoiceMapper = salesInvoiceMapper;
        this.companyRepository = companyRepository;
        this.invoiceProductRepository = invoiceProductRepository;
    }


    @Override
    public List<InvoiceDTO> listAllSalesInvoice(InvoiceType type) {
        Company company = getCurrentCompany();
        List<Invoice> invoices = salesInvoiceRepository.findInvoicesByInvoiceTypeAndCompany(type, company);
        List<InvoiceDTO> listDTO = invoices.stream()
                .map(invoiceObj -> mapperUtil.convert(invoiceObj, new InvoiceDTO()))
                .collect(Collectors.toList());
        return listDTO.stream()
                .map(this::getSalesInvoiceCost)
                .map(this::getSalesInvoiceTax)
                .map(this::getSalesInvoiceTotalCost)
                .collect(Collectors.toList());    }

    public InvoiceDTO getSalesInvoiceCost(InvoiceDTO salesInvoiceDTO){
        List<InvoiceProduct> listInvoiceProducts = invoiceProductRepository.findAllInvoiceProductsByInvoiceIdAndIsDeleted(salesInvoiceDTO.getId(), false);
        int costWithoutTax = 0;
        for (InvoiceProduct each:listInvoiceProducts) {
            costWithoutTax += each.getQty() * each.getPrice();
        }
        salesInvoiceDTO.setInvoiceCost(costWithoutTax);
        return salesInvoiceDTO;
    }

    public InvoiceDTO getSalesInvoiceTax(InvoiceDTO salesInvoiceDTO){
        List<InvoiceProduct> listInvoiceProducts = invoiceProductRepository.findAllInvoiceProductsByInvoiceIdAndIsDeleted(salesInvoiceDTO.getId(), false);
        int totalTax = 0;
        for (InvoiceProduct each:listInvoiceProducts) {
            totalTax += (each.getQty() * each.getPrice() * each.getTax())/100;
        }
        salesInvoiceDTO.setInvoiceTax(totalTax);
        return salesInvoiceDTO;
    }

    public InvoiceDTO getSalesInvoiceTotalCost(InvoiceDTO salesInvoiceDTO){
        List<InvoiceProduct> listInvoiceProducts = invoiceProductRepository.findAllInvoiceProductsByInvoiceIdAndIsDeleted(salesInvoiceDTO.getId(), false);
        int totalCostWithTax = 0;
        for (InvoiceProduct each:listInvoiceProducts) {
            totalCostWithTax += (each.getQty() * each.getPrice()) + (each.getQty() * each.getPrice() * each.getTax())/100;
        }
        salesInvoiceDTO.setTotalCost(totalCostWithTax);
        return salesInvoiceDTO;
    }



    @Override
    public void approveSalesInvoice(Long id) {
        Invoice salesInvoice = salesInvoiceRepository.findInvoiceById(id);
        salesInvoice.setInvoiceStatus(InvoiceStatus.APPROVED);
        salesInvoiceRepository.save(salesInvoice);
    }

    @Override
    public InvoiceDTO findSalesInvoiceById(Long id) {
        Invoice invoice = salesInvoiceRepository.getById(id);
        return mapperUtil.convert(invoice, new InvoiceDTO());
    }

    @Override
    public void updateSalesInvoice(InvoiceDTO invoiceDTO) {
        Invoice salesInvoice = salesInvoiceRepository.findInvoiceById(invoiceDTO.getId());
        Invoice convertedSalesInvoice = salesInvoiceMapper.convertToEntity(invoiceDTO);
        convertedSalesInvoice.setId(salesInvoice.getId());
        salesInvoiceRepository.save(convertedSalesInvoice);
        findSalesInvoiceById(invoiceDTO.getId());
    }

    @Override
    public void deleteSalesInvoiceById(Long id) {

        Invoice salesInvoice = salesInvoiceRepository.findInvoiceById(id);
        salesInvoice.setIsDeleted(true);
        salesInvoice.setInvoiceNumber(salesInvoice.getInvoiceNumber() + "-" + salesInvoice.getId());
        salesInvoiceRepository.save(salesInvoice);

    }

    @Override
    public void getToInvoiceById(Long id) {
        Invoice salesInvoice = salesInvoiceRepository.findInvoiceById(id);
        salesInvoice.setInvoiceStatus(InvoiceStatus.APPROVED);
        salesInvoiceRepository.save(salesInvoice);
    }

    @Override
    public InvoiceDTO calculateInvoiceCost(InvoiceDTO purchaseInvoiceDTO) {
        purchaseInvoiceDTO.setInvoiceCost(getSalesInvoiceCost(purchaseInvoiceDTO).getInvoiceCost());
        purchaseInvoiceDTO.setInvoiceTax(getSalesInvoiceTax(purchaseInvoiceDTO).getInvoiceTax());
        purchaseInvoiceDTO.setTotalCost(getSalesInvoiceTotalCost(purchaseInvoiceDTO).getTotalCost());
        return purchaseInvoiceDTO;
    }

    @Override
    public InvoiceDTO createSalesInvoice(ClientVendorDTO clientVendorDTO) {
        ClientVendor clientVendor = mapperUtil.convert(clientVendorDTO, new ClientVendor());
        Invoice purchaseInvoice = new Invoice();
        purchaseInvoice.setEnabled(true);
        purchaseInvoice.setCompany(getCurrentCompany());
        purchaseInvoice.setInvoiceDate(LocalDate.now());
        purchaseInvoice.setInvoiceNumber(getInvoiceNumber());
        purchaseInvoice.setInvoiceType(InvoiceType.SALE);
        purchaseInvoice.setInvoiceStatus(InvoiceStatus.PENDING);
        purchaseInvoice.setClientVendor(clientVendor);
        Invoice savedInvoice = salesInvoiceRepository.save(purchaseInvoice);
        return mapperUtil.convert(savedInvoice, new InvoiceDTO());
    }

    @Override
    public String getInvoiceNumber() {
        Company company = getCurrentCompany();
        List<Invoice> invoiceList = salesInvoiceRepository.findInvoicesByInvoiceTypeAndCompany(InvoiceType.SALE, company)
                .stream()
                .sorted(Comparator.comparing(Invoice::getInvoiceNumber))
                .collect(Collectors.toList());
        int number = Integer.parseInt(invoiceList.get(invoiceList.size()-1).getInvoiceNumber().substring(5).replaceAll("[^0-9]", "")) + 1;
        return "S-INV-" + String.format("%03d", number);
    }


    public Company getCurrentCompany() {
        userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return companyRepository.findById(userPrincipal.getLoggedInUserCompanyId()).get();
    }


}
