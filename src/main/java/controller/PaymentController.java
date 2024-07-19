package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
;
import service.PaymentService;


@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/mpesa")
    public ResponseEntity<String> processMpesaPayment(@RequestParam Long shopId, @RequestParam double amount) {
        return ResponseEntity.ok(paymentService.processMpesaPayment(shopId, amount));
    }

    @PostMapping("/paypal")
    public ResponseEntity<String> processPaypalPayment(@RequestParam Long shopId, @RequestParam double amount, @RequestParam String currency, @RequestParam String paymentMethod) {
        return ResponseEntity.ok(paymentService.processPaypalPayment(shopId, amount, currency, paymentMethod));
    }

    @PostMapping("/stripe")
    public ResponseEntity<String> processStripePayment(@RequestParam Long shopId, @RequestParam double amount, @RequestParam String currency, @RequestParam String source) {
        return ResponseEntity.ok(paymentService.processStripePayment(shopId, amount, currency, source));
    }
}
