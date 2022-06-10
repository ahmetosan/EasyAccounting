package com.easyaccounting.controller;

import com.easyaccounting.dto.PaymentDTO;
import com.easyaccounting.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Value("${app.stripePublicKey}")
    private String stripePublicKey;

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

    @GetMapping("/newpayment/{id}")
    public String getPaymentMethod(Model model, @PathVariable("id") Long id) {

        model.addAttribute("modelId", id);
        model.addAttribute("currency", "EUR");
        model.addAttribute("stripePublicKey", stripePublicKey);

        model.addAttribute("amount", paymentService.findPaymentById(id).getAmount());


        return "/payment/message";
    }

    @PostMapping("/charge/{id}")
    public String chargePayment(@PathVariable("id") Long id) {
        try {
            paymentService.chargePaymentById(id);
            return "redirect:/payment/charge/" + id;
        } catch(Exception exception) {
            exception.printStackTrace();
            return "/error";
        }
    }

    @GetMapping("/charge/{id}")
    public String getPaymentSuccess(Model model, @PathVariable("id") Long id) {
        PaymentDTO paymentDTO = paymentService.findPaymentById(id);

        if (!paymentDTO.getIsPaid()) {
            return "redirect:/payment/list";
        }

        model.addAttribute("payment", paymentDTO);

        return "/payment/payment-success";
    }
}
