package com.example.inditex.service.marshaller;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.example.inditex.model.SelectedPrice;
import com.example.inditex.service.marshaller.impl.MarshallerServiceImpl;
import com.example.inditex.utils.TestObjectBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MarshallerServiceImplTest {

    private SelectedPrice selectedPrice;

    @InjectMocks
    private MarshallerServiceImpl marshallerService;

    @Mock
    private ObjectMapper mockMapper;

    @BeforeAll
    void setUp() {
        openMocks(this);
        selectedPrice = TestObjectBuilder.buildSelectedPrice();
    }

    @Test
    void shouldMarshallPriceSuccessfully() throws JsonProcessingException {
        String priceExpected = "{price marshalled string}";
        when(mockMapper.writeValueAsString(selectedPrice)).thenReturn(priceExpected);
        String priceMarshalled = marshallerService.marshallPrice(selectedPrice);

        assertEquals(priceExpected, priceMarshalled);
    }

    @Test
    void shouldReturnNull() throws JsonProcessingException {
        when(mockMapper.writeValueAsString(selectedPrice)).thenReturn("null");
        String priceMarshalled = marshallerService.marshallPrice(selectedPrice);
        assertNull(priceMarshalled);
    }

    @Test
    void shouldLogErrorAndReturnNullWhenJsonProcessingException() throws JsonProcessingException {
        Logger logger = (Logger) LoggerFactory.getLogger(MarshallerServiceImpl.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);

        when(mockMapper.writeValueAsString(selectedPrice)).thenThrow(JsonProcessingException.class);
        String priceMarshalled = marshallerService.marshallPrice(selectedPrice);

        List<ILoggingEvent> logList = listAppender.list;
        assertEquals("Something went wrong while marshalling", logList.get(0).getMessage());
        assertEquals(Level.ERROR, logList.get(0).getLevel());
        assertEquals(1, logList.size());
        assertNull(priceMarshalled);
    }
}
