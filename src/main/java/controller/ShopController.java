package controller;


import model.PaymentDetails;
import model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.PaymentDetailsService;
import service.ShopService;

@RestController
@RequestMapping("/api/shops")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @Autowired
    private PaymentDetailsService paymentDetailsService;

    @PostMapping
    public ResponseEntity<Shop> createShop(@RequestBody Shop shop) {
        return ResponseEntity.ok(shopService.saveShop(shop));
    }

    @PostMapping("/{shopId}/payment-details")
    public ResponseEntity<PaymentDetails> savePaymentDetails(@PathVariable Long shopId, @RequestBody PaymentDetails paymentDetails) {
        paymentDetails.setShopId(shopId);
        return ResponseEntity.ok(paymentDetailsService.savePaymentDetails(paymentDetails));
    }

    @GetMapping("/{shopId}/payment-details")
    public ResponseEntity<PaymentDetails> getPaymentDetails(@PathVariable Long shopId) {
        return paymentDetailsService.getPaymentDetailsByShopId(shopId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
