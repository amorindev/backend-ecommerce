package com.fernando.backend_ecommerce.paypal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
@RestController
@RequestMapping("/payment")
public class PaypalController {

    private final PaypalService paypalService;

    public PaypalController(PaypalService paypalService) {
        this.paypalService = paypalService;
    }

    @PostMapping("/create")
    public RedirectView createPayment() {
        try {
            String cancelUrl = "http://localhost:8005/payment/cancel";
            String successUrl = "http://localhost:8005/payment/success";

            Payment payment = paypalService.createPayment(
                10.0, 
                "USD", 
                "paypal",
                "sale", 
                "Paypal description",
                cancelUrl,
                successUrl
            );

            for (Links links : payment.getLinks()) {
                if(links.getRel().equals("approval_url")){
                    return new RedirectView(links.getHref());
                }
            }

        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        
        return new RedirectView("/payment/error");
    }
    
    
    @GetMapping("/success")
    public String paymentSuccess(
        @RequestParam("paymentId") String paymentId,
        @RequestParam("payerId") String payerId
    ) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){
                return "Payment successs";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "payment sucesss";
    }

    @GetMapping("/cancel")
    public String paymentCancel() {
        return "payment cancel";
    }
    
    @GetMapping("/error")
    public String paymentError() {
        return "payment error";
    }
}
