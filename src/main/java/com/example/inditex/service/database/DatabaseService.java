package com.example.inditex.service.database;

import com.example.inditex.model.PriceIdentifier;
import com.example.inditex.model.SelectedPrice;

public interface DatabaseService {


    SelectedPrice getPrice(PriceIdentifier priceIdentifier);
}
