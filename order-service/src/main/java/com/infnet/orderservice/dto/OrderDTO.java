package com.infnet.orderservice.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private Long id;
    private String bookTitle;
    private int quantity;
    private double totalPrice;

    // Getters and Setters
}