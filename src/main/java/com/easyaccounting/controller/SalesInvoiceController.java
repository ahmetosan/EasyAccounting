package com.easyaccounting.controller;

import com.easyaccounting.dto.SalesInvoiceDTO;
import com.easyaccounting.enums.InvoiceType;
import com.easyaccounting.service.SalesInvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        model.addAttribute("salesInvoices", salesInvoiceService.listAllSalesInvoice(InvoiceType.SALES));
        model.addAttribute("salesInvoice" , new SalesInvoiceDTO());
        return "/invoice/sales-invoice-list";
        /*
        test
         */
    }

}
