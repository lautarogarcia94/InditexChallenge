package com.example.inditex.service.database.dao;

import com.example.inditex.entity.Prices;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PricesDao extends CrudRepository<Prices, Long> {

    @Query("from Prices where startDate<=?1 and endDate>?1 and brandId=?2 and productId=?3 order by priority desc ")
    List<Prices> getValidPrice(LocalDateTime applicationDate, int brandId, int productId);
}
