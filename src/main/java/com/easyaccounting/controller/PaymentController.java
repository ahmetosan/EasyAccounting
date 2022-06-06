package com.easyaccounting.controller;

import com.easyaccounting.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/list")
    public String getPaymentList(Model model, @RequestParam(defaultValue = "2022") String year) {

        model.addAttribute("localDateTime", LocalDateTime.now());
        model.addAttribute("year", year);
        model.addAttribute("payments", paymentService.findAllByYear(year));

        return "/payment/payment-list";
    }
}
