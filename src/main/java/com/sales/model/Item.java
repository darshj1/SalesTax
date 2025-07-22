package com.sales.model;

public class Item {
    private String name;
    private Double price;
    private Integer quantity;
    private Boolean isImported;
    private Boolean isExempt;

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Boolean getImported() {
        return isImported;
    }

    public Boolean getExempt() {
        return isExempt;
    }

    public Item(String name, Double price, Integer quantity, Boolean isImported, Boolean isExempt) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.isImported = isImported;
        this.isExempt = isExempt;
    }
}
