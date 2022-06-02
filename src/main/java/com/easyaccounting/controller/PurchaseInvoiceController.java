package com.easyaccounting.controller;

import com.easyaccounting.service.PurchaseInvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PurchaseInvoiceController {

    private final PurchaseInvoiceService invoiceService;

    public PurchaseInvoiceController(PurchaseInvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoice/purchase-invoice-list.html")
    public String getPurchaseInvoices(Model model){
        model.addAttribute("purchaseInvoices", invoiceService.listAllPurchaseInvoices());
        return "invoice/purchase-invoice-list";
    }

//    @GetMapping("/invoice/invoice/purchase-invoice-list/")
//    public String getAllPurchaseInvoices(Model model){
//        model.addAttribute("purchaseInvoices", invoiceService.listAllPurchaseInvoices());
//        return "invoice/purchase-invoice-list";
//    }
}
