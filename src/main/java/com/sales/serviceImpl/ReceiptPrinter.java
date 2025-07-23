package com.sales.serviceImpl;

import com.sales.model.Item;
import com.sales.model.Receipt;
import com.sales.model.ReceiptItem;

public class ReceiptPrinter {
    public void printReceipt(Receipt receipt) {
        for (ReceiptItem item : receipt.items()) {
            Item i = item.getItem();
            String label = i.isImported() ? "imported " : "";
            System.out.printf("%d %s%s: %.2f%n",
                    i.quantity(), label, i.name(), item.getTotalPriceWithTax());
        }
        System.out.printf("Sales Taxes: %.2f%n", receipt.getTotalSalesTax());
        System.out.printf("Total: %.2f%n", receipt.getTotalAmount());
    }
}