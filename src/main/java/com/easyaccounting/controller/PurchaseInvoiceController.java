package com.easyaccounting.controller;

import com.easyaccounting.dto.ClientVendorDTO;
import com.easyaccounting.dto.InvoiceDTO;
import com.easyaccounting.dto.InvoiceProductDTO;
import com.easyaccounting.enums.ClientVendorType;
import com.easyaccounting.enums.InvoiceType;
import com.easyaccounting.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/purchase-invoice")
public class PurchaseInvoiceController {

    private final PurchaseInvoiceService invoiceService;
    private final InvoiceProductService invoiceProductService;
    private final ClientVendorService clientVendorService;
    private final ProductService productService;
    private final CompanyService companyService;

    public PurchaseInvoiceController(PurchaseInvoiceService invoiceService, InvoiceProductService invoiceProductService, ClientVendorService clientVendorService, ProductService productService, CompanyService companyService) {
        this.invoiceService = invoiceService;
        this.invoiceProductService = invoiceProductService;
        this.clientVendorService = clientVendorService;
        this.productService = productService;
        this.companyService = companyService;
    }

    @GetMapping("/list")
    public String getPurchaseInvoices(Model model){
        model.addAttribute("invoices", invoiceService.listAllPurchaseInvoices(InvoiceType.PURCHASE));
        model.addAttribute("invoice", new InvoiceDTO());
        model.addAttribute("clientVendor", new ClientVendorDTO());
        model.addAttribute("clientVendors", clientVendorService.getAllClientVendorsByCompanyType(ClientVendorType.VENDOR));
        model.addAttribute("company", companyService.getCurrentCompany().getTitle());
        return "/invoice/purchase-invoice-list";
    }

    @GetMapping("/approve/{id}")
    public String approvePurchaseInvoiceById(@PathVariable("id") Long id){
        invoiceService.approvePurchaseInvoice(id);
        return "redirect:/purchase-invoice/list";
    }

    @GetMapping("/delete/{id}")
    public String deletePurchaseInvoiceById(@PathVariable("id") Long id){
        invoiceService.deletePurchaseInvoiceById(id);
        return "redirect:/purchase-invoice/list";
    }

    @GetMapping("/toInvoice/{id}")
    public String getToPurchaseInvoiceById(@PathVariable("id") Long id, Model model){
        invoiceService.getToInvoiceById(id);
        InvoiceDTO invoiceDTO = invoiceService.findPurchaseInvoiceById(id);
        InvoiceDTO invoiceCostDTO = invoiceService.calculateInvoiceCost(invoiceDTO);
        model.addAttribute("invoice", invoiceCostDTO);
        model.addAttribute("product", productService.getProductByCompany());
        model.addAttribute("company", companyService.findCompanyById(invoiceDTO.getCompany().getId()));
        model.addAttribute("invoiceProducts", invoiceProductService.getAllInvoiceProductsById(id));

        return "/invoice/toInvoice";
    }

    @GetMapping( "/update/{id}")
    public String editPurchaseInvoiceById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("invoice", invoiceService.findPurchaseInvoiceById(id));
        model.addAttribute("invoiceProducts", invoiceProductService.getAllInvoiceProductsById(id));
        model.addAttribute("clientVendor", invoiceService.findPurchaseInvoiceById(id).getClientVendor());
        model.addAttribute("invoiceProduct", new InvoiceProductDTO());
        model.addAttribute("products", productService.getAllProductsByCompany());
        return "invoice/purchase-invoice-update";
    }

    @PostMapping("/update/add-invoice-product/{id}")
    public String addInvoiceProduct(@PathVariable("id") Long id, InvoiceProductDTO invoiceProduct) {
        InvoiceDTO invoiceDTO = invoiceService.findPurchaseInvoiceById(id);
        invoiceProductService.saveInvoiceProduct(invoiceProduct, invoiceDTO);
        return "redirect:/purchase-invoice/update/" + id;

    }

    @PostMapping("/update/delete-invoice-product/{invoiceId}/{id}")
    public String deleteInvoiceProduct(@PathVariable("invoiceId") Long invoiceId, @PathVariable("id") Long id){
       invoiceProductService.deleteInvoiceProduct(id);
        return "redirect:/purchase-invoice/update/"+invoiceId;
    }

    @GetMapping("/create-invoice")
    public String createInvoice(ClientVendorDTO clientVendorDTO) {
        InvoiceDTO invoiceDTO = invoiceService.createPurchaseInvoice(clientVendorDTO);
        return "redirect:/purchase-invoice/update/"+invoiceDTO.getId();
    }
}
