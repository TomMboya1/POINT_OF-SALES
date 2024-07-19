package service;

import model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ShopRepository;

@Service
public class ShopService {
    @Autowired
    private ShopRepository shopRepository;

    public Shop saveShop(Shop shop) {
        return shopRepository.save(shop);
    }
}
