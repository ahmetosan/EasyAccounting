package com.easyaccounting.controller;

import com.easyaccounting.dto.PurchaseInvoiceDTO;
import com.easyaccounting.enums.InvoiceType;
import com.easyaccounting.service.PurchaseInvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/invoice")
public class PurchaseInvoiceController {

    private final PurchaseInvoiceService invoiceService;

    public PurchaseInvoiceController(PurchaseInvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/purchase-invoice-list")
    public String getPurchaseInvoices(Model model){
        model.addAttribute("purchaseInvoices", invoiceService.listAllPurchaseInvoices(InvoiceType.PURCHASE));
        model.addAttribute("purchaseInvoice", new PurchaseInvoiceDTO());
        return "/invoice/purchase-invoice-list";
    }

    @GetMapping("/purchase-invoice-create")
    public String createPurchaseInvoice(Model model){
        model.addAttribute("invoice", new PurchaseInvoiceDTO());
        return "/invoice/purchase-invoice-create";
    }

    // refactor when implementing crud
    @GetMapping("/approve/{purchaseInvoiceNumber}")
    public String approvePurchaseInvoice(@PathVariable("purchaseInvoiceNumber") String purchaseInvoiceNumber) {
        invoiceService.approvePurchaseInvoice(purchaseInvoiceNumber);
        return "redirect:/invoice/purchase-invoice-list";
    }


    @GetMapping("/purchase-invoice-list/delete/{id}")
    public String deleteInvoiceByInvoiceNumber(@PathVariable("id") Long id){
        invoiceService.deletePurchaseInvoiceById(id);
        return "redirect:/invoice/purchase-invoice-list";
    }
}
