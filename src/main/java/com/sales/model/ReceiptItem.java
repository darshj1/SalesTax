package com.sales.model;

public record ReceiptItem(Item item, Double totalPriceWithTax,Double totalTax){
    @Override
    public String toString() {
        return "ReceiptItem{" +
                "item=" + item +
                ", totalPriceWithTax=" + totalPriceWithTax +
                ", totalTax=" + totalTax +
                '}';
    }
}