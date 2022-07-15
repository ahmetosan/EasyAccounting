package com.easyaccounting.controller;

import com.easyaccounting.dto.InvoiceDTO;
import com.easyaccounting.enums.InvoiceType;
import com.easyaccounting.service.SalesInvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/approve/{id}")
    public String approveSalesInvoiceById(@PathVariable("id") Long id) {
        salesInvoiceService.approveSalesInvoice(id);
        return "redirect:/invoice/sales-invoice-list";
    }


    @GetMapping("/delete/{id}")
    public String deleteSalesInvoiceById(@PathVariable("id") Long id){
        salesInvoiceService.deleteSalesInvoiceById(id);
        return "redirect:/invoice/sales-invoice-list";
    }

    @GetMapping("/toInvoice/{id}")
    public String getToSalesInvoiceById(@PathVariable("id") Long id){
        salesInvoiceService.getToInvoiceById(id);
        return "/invoice/toInvoice";
    }

    @GetMapping("/update/{id}")
    public String editSalesInvoiceById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("invoice", salesInvoiceService.findSalesInvoiceById(id));
        return "/invoice/sales-invoice-create";
    }

    @PostMapping("/update")
    public String updateSalesInvoice(@PathVariable("id") Long id, InvoiceDTO salesInvoiceDTO){
        salesInvoiceService.updateSalesInvoice(salesInvoiceDTO);
        return "/invoice/sales-invoice-create";
    }

}
