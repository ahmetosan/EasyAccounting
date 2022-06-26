package com.easyaccounting.controller;

import com.easyaccounting.dto.ClientVendorDTO;
import com.easyaccounting.dto.InvoiceDTO;
import com.easyaccounting.enums.InvoiceType;
import com.easyaccounting.service.ClientVendorService;
import com.easyaccounting.service.InvoiceProductService;
import com.easyaccounting.service.PurchaseInvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/purchase-invoice")
public class PurchaseInvoiceController {

    private final PurchaseInvoiceService invoiceService;
    private final InvoiceProductService invoiceProductService;
    private final ClientVendorService clientVendorService;

    public PurchaseInvoiceController(PurchaseInvoiceService invoiceService, InvoiceProductService invoiceProductService, ClientVendorService clientVendorService) {
        this.invoiceService = invoiceService;
        this.invoiceProductService = invoiceProductService;
        this.clientVendorService = clientVendorService;
    }

    @GetMapping("/list")
    public String getPurchaseInvoices(Model model){
        model.addAttribute("invoices", invoiceService.listAllPurchaseInvoices(InvoiceType.PURCHASE));
        model.addAttribute("invoice", new InvoiceDTO());
        return "/invoice/purchase-invoice-list";
    }

    @GetMapping("/create")
    public String getPurchaseInvoiceCreate(Model model){
        model.addAttribute("invoice", new InvoiceDTO());
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
        InvoiceDTO invoiceDto = invoiceService.findPurchaseInvoiceById(id);
        invoiceDto.setInvoiceProduct(invoiceProductService.getAllInvoiceProductsById(id));
        model.addAttribute("invoice", invoiceDto);
        model.addAttribute("invoiceProducts", invoiceDto.getInvoiceProduct());
        model.addAttribute("clientVendor", new ClientVendorDTO());
        model.addAttribute("clientVendors", clientVendorService.getAllClientVendorByInvoiceType(InvoiceType.PURCHASE));
        return "invoice/purchase-invoice-update";
    }

    @PostMapping("/update")
    public String updatePurchaseInvoice(@PathVariable("id") Long id, InvoiceDTO purchaseInvoiceDTO){
        invoiceService.updatePurchaseInvoice(purchaseInvoiceDTO);
        return "invoice/purchase-invoice-update";
    }

    @PostMapping("/create/add")
    public String createNewPurchaseInvoice(InvoiceDTO purchaseInvoiceDTO){
        invoiceService.savePurchaseInvoice(purchaseInvoiceDTO);
        return "/invoice/purchase-invoice-create";
    }
}
