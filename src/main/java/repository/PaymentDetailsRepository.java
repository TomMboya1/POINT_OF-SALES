package repository;


import model.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;



public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Long> {
    Optional<PaymentDetails> findByShopId(Long shopId);
}
