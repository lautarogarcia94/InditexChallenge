package com.example.inditex.service.marshaller.impl;

import com.example.inditex.constant.Constants;
import com.example.inditex.model.SelectedPrice;
import com.example.inditex.service.marshaller.MarshallerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class MarshallerServiceImpl implements MarshallerService {

    private final ObjectMapper mapper;

    @Override
    public String marshallPrice(SelectedPrice price) {
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(price);
        } catch (JsonProcessingException jsonProcessExc) {
            log.error("Something went wrong while marshalling");
        }
        return (Constants.NULL.equalsIgnoreCase(jsonString)) ? null : jsonString;
    }
}
