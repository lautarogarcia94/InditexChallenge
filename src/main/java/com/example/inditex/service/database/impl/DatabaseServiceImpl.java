package com.example.inditex.service.database.impl;

import com.example.inditex.Utils.Utils;
import com.example.inditex.entity.Prices;
import com.example.inditex.model.PriceIdentifier;
import com.example.inditex.model.SelectedPrice;
import com.example.inditex.service.database.DatabaseService;
import com.example.inditex.service.database.dao.PricesDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DatabaseServiceImpl implements DatabaseService {

    private final PricesDao pricesDao;

    public SelectedPrice getPrice(PriceIdentifier priceIdentifier) {
        LocalDateTime applicationDate = LocalDateTime.parse(priceIdentifier.getApplicationDate(), Utils.getDateTimeFormatter());
        int brandId = priceIdentifier.getBrandId();
        int productId = priceIdentifier.getProductId();

        List<Prices> pricesList = pricesDao.getValidPrice(applicationDate, brandId, productId);

        return Utils.buildSelectedPrice(pricesList.get(0));
    }
}
