package com.easyaccounting.controller;

import com.easyaccounting.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    private final CompanyService companyService;

    public DashboardController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping()
    public String getDashboard(Model model){
        model.addAttribute("company", companyService.getCurrentCompany().getTitle());
        return "dashboard";
    }

}