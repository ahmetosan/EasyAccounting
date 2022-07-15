package com.easyaccounting.controller;


import com.easyaccounting.dto.CompanyDTO;
import com.easyaccounting.enums.State;
import com.easyaccounting.service.CompanyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/company")
public class CompanyController {

    private final Logger logger = LogManager.getLogger(CompanyController.class);
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/list")
    public String addCompany(Model model){

        model.addAttribute("companies", companyService.listAllCompanies());

        return "/company/company-list";
    }


   @GetMapping("/add")
    public String addCompany2(Model model) {

        model.addAttribute("companies", companyService.listAllCompanies());
        return "/company/company-add";

    }

    @GetMapping("/company-edit/{id}")
    public String editCompany(@PathVariable("id") Long id, Model model){

        logger.info("Company to be edited: " + companyService.findCompanyById(id));
        model.addAttribute("company", companyService.findCompanyById(id));
        model.addAttribute("stateList", List.of(State.values()));
        return "/company/company-edit";
    }

    @PostMapping("/company-edit")
    public String editCompany(CompanyDTO companyDTO){
        logger.info("Company edited: " + companyDTO);
        companyService.update(companyDTO);
        return "redirect:/company/list";
    }
    @GetMapping("/close/{id}")
    public String closeCompany(@PathVariable("id") Long id){
        logger.info("Company to be deleted: " + companyService.findCompanyById(id));
        // companyService.disable(id);
        return "redirect:/company/company-list";
    }

    @GetMapping("/re-open/{id}")
    public String reOpenCompany(@PathVariable("id") Long id){
        logger.info("Company to be deleted: " + companyService.findCompanyById(id));
        //companyService.enable(id);
        return "redirect:/company/company-list";
    }

    }
