package com.billing.dto;

public class InvoiceItemDTO {

    private Long id;
    private String itemName;
    private Integer quantity;
    private Double price;
    private Double tax;
    private Double discount;

    private Double rowTotal;
    private Double cgstAmount;
    private Double sgstAmount;

    private Long productId;

    // getters & setters
}
