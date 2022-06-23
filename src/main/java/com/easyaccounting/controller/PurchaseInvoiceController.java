package com.easyaccounting.controller;

import com.easyaccounting.dto.InvoiceDTO;
import com.easyaccounting.enums.InvoiceType;
import com.easyaccounting.service.PurchaseInvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/purchase-invoice")
public class PurchaseInvoiceController {

    private final PurchaseInvoiceService invoiceService;

    public PurchaseInvoiceController(PurchaseInvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/list")
    public String getPurchaseInvoices(Model model){
        model.addAttribute("purchaseInvoices", invoiceService.listAllPurchaseInvoices(InvoiceType.PURCHASE));
        model.addAttribute("purchaseInvoice", new InvoiceDTO());
        return "/invoice/purchase-invoice-list";
    }

    @GetMapping("/create")
    public String getPurchaseInvoiceCreate(Model model){
        model.addAttribute("purchaseInvoice", new InvoiceDTO());
        return "/invoice/purchase-invoice-create";
    }

    @GetMapping("/approve/{id}")
    public String approvePurchaseInvoiceById(@PathVariable("id") Long id) {
        invoiceService.approvePurchaseInvoice(id);
        return "redirect:/purchase-invoice/list";
    }

    @GetMapping("/delete/{id}")
    public String deletePurchaseInvoiceById(@PathVariable("id") Long id){
        invoiceService.deletePurchaseInvoiceById(id);
        return "redirect:/purchase-invoice/list";
    }

    @GetMapping("/toInvoice/{id}")
    public String getToPurchaseInvoiceById(@PathVariable("id") Long id){
        invoiceService.getToInvoiceById(id);
        return "/invoice/toInvoice";
    }

    @GetMapping("/update/{id}")
    public String editPurchaseInvoiceById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("invoice", invoiceService.findPurchaseInvoiceById(id));
        return "/invoice/purchase-invoice-create";
    }

    @PostMapping("/update")
    public String updatePurchaseInvoice(@PathVariable("id") Long id, InvoiceDTO purchaseInvoiceDTO){
        invoiceService.updatePurchaseInvoice(purchaseInvoiceDTO);
        return "/invoice/purchase-invoice-create";
    }

    @PostMapping("/create/add")
    public String createNewPurchaseInvoice(InvoiceDTO purchaseInvoiceDTO){
        invoiceService.savePurchaseInvoice(purchaseInvoiceDTO);
        return "/invoice/purchase-invoice-create";
    }
}
