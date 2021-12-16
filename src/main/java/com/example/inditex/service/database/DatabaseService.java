package com.example.inditex.service.database;

import com.example.inditex.entity.Prices;
import com.example.inditex.model.PriceIdentifier;

public interface DatabaseService {


    Prices getPrice(PriceIdentifier priceIdentifier);
}
