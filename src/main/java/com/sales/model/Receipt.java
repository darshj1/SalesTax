package com.sales.model;

import java.util.List;

public record Receipt(List<ReceiptItem> items) {

    public double getTotalSalesTax() {
        return items.stream().mapToDouble(ReceiptItem::getTotalTax).sum();
    }

    public double getTotalAmount() {
        return items.stream().mapToDouble(ReceiptItem::getTotalPriceWithTax).sum();
    }
}