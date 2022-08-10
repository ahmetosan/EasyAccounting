package com.easyaccounting.controller;

import com.easyaccounting.dto.ClientVendorDTO;
import com.easyaccounting.enums.State;
import com.easyaccounting.service.ClientVendorService;
import com.easyaccounting.service.CompanyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/clientvendor")
public class ClientVendorController {
    private final Logger logger = LogManager.getLogger(ClientVendorController.class);

    private final ClientVendorService clientVendorService;
    private final CompanyService companyService;

    public ClientVendorController(ClientVendorService clientVendorService, CompanyService companyService) {
        this.clientVendorService = clientVendorService;
        this.companyService = companyService;
    }


    @GetMapping("/client-vendor-list")
    public String listClientVendors(Model model) {
        List<ClientVendorDTO> clientVendorList = clientVendorService.listAllClientVendors();
        logger.info("Client Vendor list: " + clientVendorList);
        model.addAttribute("clientVendorList", clientVendorList);
        model.addAttribute("company", companyService.getCurrentCompany().getTitle());
        return "/clientvendor/client-vendor-list";
    }

    @GetMapping("/client-vendor-add")
    public String addClientVendor(Model model) {
        model.addAttribute("clientVendor", new ClientVendorDTO());
        model.addAttribute("stateList", List.of(State.values()));
        return "/clientvendor/client-vendor-add";
    }

    @PostMapping("/client-vendor-add")
    public String addClientVendor(@ModelAttribute("clientVendor") ClientVendorDTO clientVendorDto) {
        logger.info("Client Vendor to be added: " + clientVendorDto);
        clientVendorService.save(clientVendorDto);
        return "redirect:/clientvendor/client-vendor-list";
    }

    @GetMapping("/client-vendor-edit/{clientVendorId}")
    public String editClientVendor(@PathVariable("clientVendorId") Long clientVendorId, Model model) {
        logger.info("Client Vendor to be edited: " + clientVendorService.findClientVendorById(clientVendorId));

        model.addAttribute("clientVendor", clientVendorService.findClientVendorById(clientVendorId));
        model.addAttribute("stateList", List.of(State.values()));
        return "/clientvendor/client-vendor-edit";
    }

    @PostMapping("/client-vendor-edit")
    public String editClientVendor(ClientVendorDTO clientVendorDto) {
        logger.info("Client Vendor edited = " + clientVendorDto);
        clientVendorService.update(clientVendorDto);
        return "redirect:/clientvendor/client-vendor-list";
    }

    @GetMapping("/delete/{clientVendorId}")
    public String deleteClientVendor(@PathVariable("clientVendorId") Long clientVendorId) {
        logger.info("Client Vendor to be deleted: " + clientVendorId);
        clientVendorService.delete(clientVendorId);
        return "redirect:/clientvendor/client-vendor-list";
    }


}
