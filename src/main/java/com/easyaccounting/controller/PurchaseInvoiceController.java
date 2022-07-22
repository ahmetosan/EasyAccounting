package com.easyaccounting.controller;

import com.easyaccounting.dto.ClientVendorDTO;
import com.easyaccounting.dto.InvoiceDTO;
import com.easyaccounting.dto.InvoiceProductDTO;
import com.easyaccounting.enums.ClientVendorType;
import com.easyaccounting.enums.InvoiceType;
import com.easyaccounting.service.ClientVendorService;
import com.easyaccounting.service.InvoiceProductService;
import com.easyaccounting.service.ProductService;
import com.easyaccounting.service.PurchaseInvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/purchase-invoice")
public class PurchaseInvoiceController {

    private final PurchaseInvoiceService invoiceService;
    private final InvoiceProductService invoiceProductService;
    private final ClientVendorService clientVendorService;
    private final ProductService productService;

    public PurchaseInvoiceController(PurchaseInvoiceService invoiceService, InvoiceProductService invoiceProductService, ClientVendorService clientVendorService, ProductService productService) {
        this.invoiceService = invoiceService;
        this.invoiceProductService = invoiceProductService;
        this.clientVendorService = clientVendorService;
        this.productService = productService;
    }

    @GetMapping("/list")
    public String getPurchaseInvoices(Model model){
        model.addAttribute("invoices", invoiceService.listAllPurchaseInvoices(InvoiceType.PURCHASE));
        model.addAttribute("invoice", new InvoiceDTO());
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
    public String getToPurchaseInvoiceById(@PathVariable("id") Long id){
        invoiceService.getToInvoiceById(id);
        return "/invoice/toInvoice";
    }

    @GetMapping({"/update", "/update/{id}"})
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
        invoiceProduct.setInvoice(invoiceDTO);
        invoiceProduct.setId(null);
        invoiceProductService.saveInvoiceProduct(invoiceProduct);
        return "redirect:/purchase-invoice/update/" + id;

    }

    @PostMapping("/update/delete-invoice-product/{invoiceId}/{id}")
    public String deleteInvoiceProduct(@PathVariable("invoiceId") Long invoiceId, @PathVariable("id") Long id){
       invoiceProductService.deleteInvoiceProduct(id);
        return "redirect:/purchase-invoice/update/"+invoiceId;
    }

    @PostMapping("/update/{id}")
    public String updatePurchaseInvoice(@PathVariable("id") Long id, InvoiceDTO purchaseInvoiceDTO){
        invoiceService.updatePurchaseInvoice(purchaseInvoiceDTO, id);
//        invoiceProductService.updateInvoiceProduct(id, purchaseInvoiceDTO.getInvoiceProduct());
        return "redirect:invoice/purchase-invoice-list";
    }

    @GetMapping("/create")
    public String getPurchaseInvoiceCreate(Model model){
        model.addAttribute("invoice", new InvoiceDTO());
        model.addAttribute("clientVendor", new ClientVendorDTO());
        model.addAttribute("clientVendors", clientVendorService.getAllClientVendorsByCompanyType(ClientVendorType.VENDOR));
        model.addAttribute("product", new InvoiceProductDTO());
        model.addAttribute("products", productService.getAllProductsByCompany());
        return "/invoice/purchase-invoice-create";
    }

    @PostMapping("/create/add-purchase-invoice/{id}")
    public String createNewPurchaseInvoice(@PathVariable("id") Long id, Model model, InvoiceDTO purchaseInvoiceDTO){
        invoiceService.savePurchaseInvoice(purchaseInvoiceDTO);
        return "/invoice/purchase-invoice-create";
    }
}
