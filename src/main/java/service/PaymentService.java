package service;



import exception.PaymentException;
import model.PaymentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import repository.PaymentDetailsRepository;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {
    @Autowired
    private PaymentDetailsRepository paymentDetailsRepository;

    @Autowired
    private RestTemplate restTemplate;

    public String processMpesaPayment(Long shopId, double amount) {
        PaymentDetails paymentDetails = paymentDetailsRepository.findByShopId(shopId)
                .orElseThrow(() -> new PaymentException("Payment details not found for shop " + shopId));

        try {
            Map<String, Object> request = new HashMap<>();
            request.put("amount", amount);
            request.put("phoneNumber", paymentDetails.getMpesaNumber());
            request.put("apiKey", "your_mpesa_api_key");

            String response = restTemplate.postForObject("mpesa_api_url", request, String.class);
            return "MPESA payment of " + amount + " processed successfully: " + response;
        } catch (Exception e) {
            throw new PaymentException("MPESA payment failed", e);
        }
    }

    public String processPaypalPayment(Long shopId, double amount, String currency, String paymentMethod) {
        PaymentDetails paymentDetails = paymentDetailsRepository.findByShopId(shopId)
                .orElseThrow(() -> new PaymentException("Payment details not found for shop " + shopId));

        try {
            Map<String, Object> request = new HashMap<>();
            request.put("amount", amount);
            request.put("currency", currency);
            request.put("paymentMethod", paymentMethod);
            request.put("clientId", "your_paypal_client_id");
            request.put("clientSecret", "your_paypal_client_secret");

            String response = restTemplate.postForObject("paypal_api_url", request, String.class);
            return "PayPal payment of " + amount + " processed successfully: " + response;
        } catch (Exception e) {
            throw new PaymentException("PayPal payment failed", e);
        }
    }

    public String processStripePayment(Long shopId, double amount, String currency, String source) {
        PaymentDetails paymentDetails = paymentDetailsRepository.findByShopId(shopId)
                .orElseThrow(() -> new PaymentException("Payment details not found for shop " + shopId));

        try {
            Map<String, Object> request = new HashMap<>();
            request.put("amount", (int) (amount * 100)); // Amount in cents
            request.put("currency", currency);
            request.put("source", source);
            request.put("apiKey", "your_stripe_api_key");

            String response = restTemplate.postForObject("stripe_api_url", request, String.class);
            return "Stripe payment of " + amount + " processed successfully: " + response;
        } catch (Exception e) {
            throw new PaymentException("Stripe payment failed", e);
        }
    }
}
