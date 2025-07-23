package com.sales.serviceImpl;

import com.sales.model.Item;
import com.sales.model.ReceiptItem;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaxCalculateServiceImplTests {
    private final TaxCalculatorServiceImpl service = new TaxCalculatorServiceImpl();

    @Test
    void shouldCalculateTaxesCorrectly() {
        Item item = new Item("imported perfume", 47.50, true, false, 1);
        List<ReceiptItem> receiptItems = service.calculateTaxes(List.of(item));

        assertEquals(1, receiptItems.size());
        ReceiptItem receiptItem = receiptItems.get(0);
        assertEquals(7.15, receiptItem.getTotalTax(), 0.01);
        assertEquals(54.65, receiptItem.getTotalPriceWithTax(), 0.01);
    }
}