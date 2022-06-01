package com.easyaccounting.controller;

import com.easyaccounting.dto.InvoiceDTO;
import com.easyaccounting.service.InvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoice/purchase-invoice-list.html")
    public String getPurchaseInvoices(Model model){
        model.addAttribute("purchaseInvoices", invoiceService.listAllPurchaseInvoices());
        return "invoice/purchase-invoice-list";
    }

    @GetMapping("/invoice/invoice/purchase-invoice-list/")
    public String getAllPurchaseInvoices(Model model){
        model.addAttribute("purchaseInvoices", invoiceService.listAllPurchaseInvoices());
        return "invoice/purchase-invoice-list";
    }
}
