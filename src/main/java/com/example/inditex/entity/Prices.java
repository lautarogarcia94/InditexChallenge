package com.example.inditex.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "prices")
public class Prices {

    @Id
    @Column(name = "id")
    private double id;

    @Column(name = "brand_id")
    private int brandId;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "price_list")
    private int priceList;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "priority")
    private int priority;

    @Column(name = "price")
    private double price;

    @Column(name = "curr")
    private String curr;
}
