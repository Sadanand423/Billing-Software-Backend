package com.billing.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "invoice_items")
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity = 0;

    @Column(nullable = false)
    private Double price = 0.0;

    private String itemName;

    // Tax and discount percentages
    @Column(nullable = false)
    private Double tax = 0.0;        // e.g., 18 for 18%

    @Column(nullable = false)
    private Double discount = 0.0;   // e.g., 10 for 10%

    // Calculated amounts
    @Column(nullable = false)
    private Double rowTotal = 0.0;

    @Column(nullable = false)
    private Double cgstAmount = 0.0;

    @Column(nullable = false)
    private Double sgstAmount = 0.0;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    @JsonBackReference
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // ===== Getters & Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public Double getTax() { return tax; }
    public void setTax(Double tax) { this.tax = tax; }

    public Double getDiscount() { return discount; }
    public void setDiscount(Double discount) { this.discount = discount; }

    public Double getRowTotal() { return rowTotal; }
    public void setRowTotal(Double rowTotal) { this.rowTotal = rowTotal; }

    public Double getCgstAmount() { return cgstAmount; }
    public void setCgstAmount(Double cgstAmount) { this.cgstAmount = cgstAmount; }

    public Double getSgstAmount() { return sgstAmount; }
    public void setSgstAmount(Double sgstAmount) { this.sgstAmount = sgstAmount; }

    public Invoice getInvoice() { return invoice; }
    public void setInvoice(Invoice invoice) { this.invoice = invoice; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
}
