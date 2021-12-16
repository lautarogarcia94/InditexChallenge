package com.example.inditex.Utils;

import com.example.inditex.constant.Constants;
import com.example.inditex.model.PriceIdentifier;
import com.example.inditex.model.SelectedPrice;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Marshaller {

    private ObjectMapper objectMapper;

    public Marshaller() {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.registerModule(new JavaTimeModule());
    }

    public String marshallPriceIdentifier(PriceIdentifier price) {
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(price);
        } catch (JsonProcessingException jsonProcessExc) {
            log.error("Something went wrong while marshalling");
        }
        return (Constants.NULL.equalsIgnoreCase(jsonString)) ? null : jsonString;
    }

    public String marshallSelectedPrice(SelectedPrice selectedPrice) {
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(selectedPrice);
        } catch (JsonProcessingException jsonProcessExc) {
            log.error("Something went wrong while marshalling");
        }
        return (Constants.NULL.equalsIgnoreCase(jsonString)) ? null : jsonString;
    }
}
