package com.easyaccounting.service.impl;

import com.easyaccounting.dto.InvoiceDTO;
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
   List<Invoice> invoices = salesInvoiceRepository.findInvoicesByInvoiceTypeAndCompany(type , company);

       return invoices.stream().map(salesInvoice -> mapperUtil.convert(salesInvoice, new InvoiceDTO())).collect(Collectors.toList());
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
    public void saveSalesInvoice(InvoiceDTO salesInvoiceDto) {
        salesInvoiceDto.setInvoiceStatus(String.valueOf(InvoiceStatus.PENDING));
        Invoice salesInvoice = salesInvoiceMapper.convertToEntity(salesInvoiceDto);
        salesInvoiceRepository.save(salesInvoice);
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

    public Company getCurrentCompany() {
        userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return companyRepository.findById(userPrincipal.getLoggedInUserCompanyId()).get();
    }


}
