package com.easyaccounting.controller;


import com.easyaccounting.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/company")
public class CompanyController {

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

        @GetMapping("/edit/{id}")
        public String editCompany(@PathVariable("id") String id) {

            return "/company/company-edit";

        }

    }
