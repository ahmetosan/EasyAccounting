package com.easyaccounting.controller;

import com.easyaccounting.dto.InvoiceDTO;
import com.easyaccounting.enums.InvoiceType;
import com.easyaccounting.service.SalesInvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/invoice")
@Controller
public class SalesInvoiceController {
       private final SalesInvoiceService salesInvoiceService;


    public SalesInvoiceController(SalesInvoiceService salesInvoiceService) {
        this.salesInvoiceService = salesInvoiceService;
    }

    @GetMapping("/sales-invoice-list")
    public String getSalesInvoice (Model model) {
        model.addAttribute("salesInvoices", salesInvoiceService.listAllSalesInvoice(InvoiceType.SALE));
        model.addAttribute("salesInvoice" , new InvoiceDTO());
        return "/invoice/sales-invoice-list";
    }

    @GetMapping("/sales-invoice-create")
    public String createSalesInvoice(Model model){
        model.addAttribute("invoice", new InvoiceDTO());
        return "/invoice/sales-invoice-create";
    }

    @GetMapping("/approve/{SalesInvoiceNumber}")
    public String approvePurchaseInvoice(@PathVariable("salesInvoiceNumber") String salesInvoiceNumber) {
        salesInvoiceService.approveSalesInvoice(salesInvoiceNumber);
        return "redirect:/invoice/purchase-invoice-list";
    }

}
