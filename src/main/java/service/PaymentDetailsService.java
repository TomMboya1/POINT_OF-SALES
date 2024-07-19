package service;



import model.PaymentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PaymentDetailsRepository;

import java.util.Optional;

@Service
public class PaymentDetailsService {
    @Autowired
    private PaymentDetailsRepository paymentDetailsRepository;

    public PaymentDetails savePaymentDetails(PaymentDetails paymentDetails) {
        return paymentDetailsRepository.save(paymentDetails);
    }

    public Optional<PaymentDetails> getPaymentDetailsByShopId(Long shopId) {
        return paymentDetailsRepository.findByShopId(shopId);
    }
}
