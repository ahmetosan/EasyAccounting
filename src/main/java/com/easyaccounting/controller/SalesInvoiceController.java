package com.easyaccounting.controller;

import com.easyaccounting.dto.ClientVendorDTO;
import com.easyaccounting.dto.InvoiceDTO;
import com.easyaccounting.dto.InvoiceProductDTO;
import com.easyaccounting.enums.ClientVendorType;
import com.easyaccounting.enums.InvoiceType;
import com.easyaccounting.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/sales-invoice")
@Controller
public class SalesInvoiceController {
        private final SalesInvoiceService salesInvoiceService;
        private final InvoiceProductService invoiceProductService;
        private final ClientVendorService clientVendorService;
        private final ProductService productService;
        private final CompanyService companyService;

    public SalesInvoiceController(SalesInvoiceService salesInvoiceService, InvoiceProductService invoiceProductService, ClientVendorService clientVendorService, ProductService productService, CompanyService companyService) {
        this.salesInvoiceService = salesInvoiceService;
        this.invoiceProductService = invoiceProductService;
        this.clientVendorService = clientVendorService;
        this.productService = productService;
        this.companyService = companyService;
    }

    @GetMapping("/list")
    public String getSalesInvoice (Model model) {
        model.addAttribute("salesInvoices", salesInvoiceService.listAllSalesInvoice(InvoiceType.SALE));
        model.addAttribute("salesInvoice" , new InvoiceDTO());
        model.addAttribute("clientVendor", new ClientVendorDTO());
        model.addAttribute("clientVendors", clientVendorService.getAllClientVendorsByCompanyType(ClientVendorType.CLIENT));

        return "/invoice/sales-invoice-list";
    }

    @GetMapping("/approve/{id}")
    public String approveSalesInvoiceById(@PathVariable("id") Long id) {
        salesInvoiceService.approveSalesInvoice(id);
        return "redirect:/sales-invoice/list";
    }


    @GetMapping("/delete/{id}")
    public String deleteSalesInvoiceById(@PathVariable("id") Long id){
        salesInvoiceService.deleteSalesInvoiceById(id);
        return "redirect:/sales-invoice/list";
    }

    @GetMapping("/toInvoice/{id}")
    public String getToSalesInvoiceById(@PathVariable("id") Long id, Model model){
        salesInvoiceService.getToInvoiceById(id);
        InvoiceDTO invoiceDTO = salesInvoiceService.findSalesInvoiceById(id);
        InvoiceDTO invoiceCostDTO = salesInvoiceService.calculateInvoiceCost(invoiceDTO);
        model.addAttribute("invoice", invoiceCostDTO);
        model.addAttribute("product", productService.getProductByCompany());
        model.addAttribute("company", companyService.findCompanyById(invoiceDTO.getCompany().getId()));
        model.addAttribute("invoiceProducts", invoiceProductService.getAllInvoiceProductsById(id));

        return "/invoice/toInvoice";
    }

    @GetMapping("/update/{id}")
    public String editSalesInvoiceById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("invoice", salesInvoiceService.findSalesInvoiceById(id));
        model.addAttribute("invoiceProducts", invoiceProductService.getAllInvoiceProductsById(id));
        model.addAttribute("clientVendor", salesInvoiceService.findSalesInvoiceById(id).getClientVendor());
        model.addAttribute("invoiceProduct", new InvoiceProductDTO());
        model.addAttribute("products", productService.getAllProductsByCompany());
        return "invoice/sales-invoice-update";
    }

    @PostMapping("/update/add-invoice-product/{id}")
    public String addInvoiceProduct(@PathVariable("id") Long id, InvoiceProductDTO invoiceProduct) {
        InvoiceDTO invoiceDTO = salesInvoiceService.findSalesInvoiceById(id);
        invoiceProductService.saveInvoiceProduct(invoiceProduct, invoiceDTO);
        return "redirect:/sales-invoice/update/" + id;

    }

    @PostMapping("/update/delete-invoice-product/{invoiceId}/{id}")
    public String deleteInvoiceProduct(@PathVariable("invoiceId") Long invoiceId, @PathVariable("id") Long id){
        invoiceProductService.deleteInvoiceProduct(id);
        return "redirect:/sales-invoice/update/"+invoiceId;
    }

    @GetMapping("/create-invoice")
    public String createInvoice(ClientVendorDTO clientVendorDTO) {
        InvoiceDTO invoiceDTO = salesInvoiceService.createSalesInvoice(clientVendorDTO);
        return "redirect:/sales-invoice/update/"+invoiceDTO.getId();
    }

}
