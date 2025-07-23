package com.sales.model;

public class ReceiptItem {
    private final Item item;
    private final double totalTax;
    private final double totalPriceWithTax;

    public ReceiptItem(Item item, double taxPerItem) {
        this.item = item;
        this.totalTax = taxPerItem * item.quantity();
        this.totalPriceWithTax = (item.price() + taxPerItem) * item.quantity();
    }

    public Item getItem() { return item; }
    public double getTotalTax() { return totalTax; }
    public double getTotalPriceWithTax() { return totalPriceWithTax; }
}