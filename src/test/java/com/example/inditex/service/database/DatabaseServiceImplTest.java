package com.example.inditex.service.database;

import com.example.inditex.constant.Constants;
import com.example.inditex.entity.Prices;
import com.example.inditex.model.PriceIdentifier;
import com.example.inditex.service.database.dao.PricesDao;
import com.example.inditex.service.database.impl.DatabaseServiceImpl;
import com.example.inditex.utils.TestObjectBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DatabaseServiceImplTest {

    private PriceIdentifier priceIdentifier;
    private Prices price;


    @Mock
    private PricesDao mockPricesDao;

    @InjectMocks
    private DatabaseServiceImpl databaseService;

    @BeforeAll
    void setUp() {
        openMocks(this);

        priceIdentifier = TestObjectBuilder.buildPriceIdentifier();
        price = TestObjectBuilder.buildPrice();
    }

    @Test
    void shouldGetPriceSuccessfully() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_PATTERN);
        LocalDateTime applicationDate = LocalDateTime.parse(priceIdentifier.getApplicationDate(), dateTimeFormatter);
        int brandId = priceIdentifier.getBrandId();
        int productId = priceIdentifier.getProductId();

        when(mockPricesDao.getValidPrice(applicationDate, brandId, productId)).thenReturn(price);

        Prices actualPrice = databaseService.getPrice(priceIdentifier);

        assertEquals(price, actualPrice);
    }
}
