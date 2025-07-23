package com.sales.model;

public record ReceiptItem(Item item, Double totalPriceWithTax,Double totalTax){
    @Override
    public String toString() {
        return "ReceiptItem{" +
                "item=" + item.getName() +
                ", totalPriceWithTax=" + totalPriceWithTax +
                ", totalTax=" + totalTax +
                '}';
    }
}