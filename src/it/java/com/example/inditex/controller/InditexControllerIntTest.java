package com.example.inditex.controller;

import com.example.inditex.InditexApplication;
import com.example.inditex.Utils.Marshaller;
import com.example.inditex.model.PriceIdentifier;
import com.example.inditex.model.SelectedPrice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = InditexApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class InditexControllerIntTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;
    private Marshaller marshaller;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        marshaller = new Marshaller();
    }

    @Test
    public void testNumero1_Dia14_Hora10() throws Exception {
        String URI = "/inditex/getProductPrice";
        LocalDateTime startDate = LocalDateTime.of(2020, 6, 14, 0, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2020, 12, 31, 23, 59, 59);

        String inputBodyJson = getJsonPriceIdentifier("2020-06-14-10.00.00", 1, 35455);
        String expectedMessage = getJsonSelectedPrice(startDate, endDate, 1, 35.5);

        mockMvc.perform(get(URI).contentType(MediaType.APPLICATION_JSON).content(inputBodyJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedMessage, true))
                .andDo(print());
    }

    @Test
    public void testNumero2_Dia14_Hora18() throws Exception {
        String URI = "/inditex/getProductPrice";
        LocalDateTime startDate = LocalDateTime.of(2020, 6, 14, 15, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2020, 6, 14, 18, 30, 0);

        String inputBodyJson = getJsonPriceIdentifier("2020-06-14-16.00.00", 1, 35455);
        String expectedMessage = getJsonSelectedPrice(startDate, endDate, 2, 25.45);

        mockMvc.perform(get(URI).contentType(MediaType.APPLICATION_JSON).content(inputBodyJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedMessage, true))
                .andDo(print());
    }

    @Test
    public void testNumero3_Dia14_Hora21() throws Exception {
        String URI = "/inditex/getProductPrice";
        LocalDateTime startDate = LocalDateTime.of(2020, 6, 14, 0, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2020, 12, 31, 23, 59, 59);

        String inputBodyJson = getJsonPriceIdentifier("2020-06-14-21.00.00", 1, 35455);
        String expectedMessage = getJsonSelectedPrice(startDate, endDate, 1, 35.5);

        mockMvc.perform(get(URI).contentType(MediaType.APPLICATION_JSON).content(inputBodyJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedMessage, true))
                .andDo(print());
    }

    @Test
    public void testNumero4_Dia15_Hora10() throws Exception {
        String URI = "/inditex/getProductPrice";
        LocalDateTime startDate = LocalDateTime.of(2020, 6, 15, 0, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2020, 6, 15, 11, 0, 0);

        String inputBodyJson = getJsonPriceIdentifier("2020-06-15-10.00.00", 1, 35455);
        String expectedMessage = getJsonSelectedPrice(startDate, endDate, 3, 30.5);

        mockMvc.perform(get(URI).contentType(MediaType.APPLICATION_JSON).content(inputBodyJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedMessage, true))
                .andDo(print());
    }

    @Test
    public void testNumero5_Dia16_Hora21() throws Exception {
        String URI = "/inditex/getProductPrice";
        LocalDateTime startDate = LocalDateTime.of(2020, 6, 15, 16, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2020, 12, 31, 23, 59, 59);

        String inputBodyJson = getJsonPriceIdentifier("2020-06-16-21.00.00", 1, 35455);
        String expectedMessage = getJsonSelectedPrice(startDate, endDate, 4, 38.95);

        mockMvc.perform(get(URI).contentType(MediaType.APPLICATION_JSON).content(inputBodyJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedMessage, true))
                .andDo(print());
    }

    private String getJsonPriceIdentifier(String applicationDate, int brandId, int productId) {
        PriceIdentifier priceIdentifier = PriceIdentifier.builder()
                .applicationDate(applicationDate)
                .brandId(brandId)
                .productId(productId)
                .build();
        return marshaller.marshallPriceIdentifier(priceIdentifier);
    }

    private String getJsonSelectedPrice(LocalDateTime startDate, LocalDateTime endDate, int priceList, double price) {
        SelectedPrice pricesEntity = SelectedPrice.builder()
                .brandId(1)
                .startDate(startDate)
                .endDate(endDate)
                .priceList(priceList)
                .productId(35455)
                .price(price)
                .build();
        return marshaller.marshallSelectedPrice(pricesEntity);
    }
}
