package com.example.inditex.service.marshaller;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.example.inditex.entity.Prices;
import com.example.inditex.service.marshaller.impl.MarshallerServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MarshallerServiceImplTest {

    private Prices price;

    @InjectMocks
    private MarshallerServiceImpl marshallerService;

    @Mock
    private ObjectMapper mockMapper;

    @BeforeAll
    void setUp() {
        openMocks(this);
        price = buildPrice();
    }

    private Prices buildPrice() {
        price = new Prices();
        price.setId(123);
        price.setBrandId(5);
        price.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        price.setEndDate(LocalDateTime.of(2020, 8, 13, 23, 0, 0));
        price.setPriceList(1);
        price.setProductId(123456);
        price.setPriority(5);
        price.setPrice(455.50);
        price.setCurr("EUR");
        return price;
    }


    @Test
    void shouldMarshallPriceSuccessfully() throws JsonProcessingException {
        String priceExpected = "{price marshalled string}";
        when(mockMapper.writeValueAsString(price)).thenReturn(priceExpected);
        String priceMarshalled = marshallerService.marshallPrice(price);

        assertEquals(priceExpected, priceMarshalled);
    }

    @Test
    void shouldReturnNull() throws JsonProcessingException {
        when(mockMapper.writeValueAsString(price)).thenReturn("null");
        String priceMarshalled = marshallerService.marshallPrice(price);
        assertNull(priceMarshalled);
    }

    @Test
    void shouldLogErrorAndReturnNullWhenJsonProcessingException() throws JsonProcessingException {
        Logger logger = (Logger) LoggerFactory.getLogger(MarshallerServiceImpl.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);

        when(mockMapper.writeValueAsString(price)).thenThrow(JsonProcessingException.class);
        String priceMarshalled = marshallerService.marshallPrice(price);

        List<ILoggingEvent> logList = listAppender.list;
        assertEquals("Something went wrong while marshalling", logList.get(0).getMessage());
        assertEquals(Level.ERROR, logList.get(0).getLevel());
        assertEquals(1, logList.size());
        assertNull(priceMarshalled);
    }
}
