package com.sales.serviceImpl;

import com.sales.model.Item;
import com.sales.model.ReceiptItem;
import com.sales.service.TaxCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CartServiceImplTests {

    @Mock
    private TaxCalculatorService taxCalculatorService;

    @InjectMocks
    private CartServiceImpl cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddItem() {
        Item item = new Item("book", 12.49, 1, false, false);
        cartService.addItem(item);

        assertEquals(1, cartService.items.size());
        assertEquals(item, cartService.items.get(0));
    }

    @Test
    void testCheckoutWithOneItem() {
        Item item = new Item("music CD", 14.99, 1, false, true); // taxable
        cartService.addItem(item);

        // Mock tax calculation
        when(taxCalculatorService.calculateTax(item)).thenReturn(1.50);

        List<ReceiptItem> receiptItems = cartService.checkout();

        assertEquals(1, receiptItems.size());
        ReceiptItem receiptItem = receiptItems.get(0);

        double expectedTotalTax = 1.50;
        double expectedTotalPrice = 14.99 + 1.50;
        double expectedTotalPriceRounded = Math.round(expectedTotalPrice * 100.0) / 100.0;
        double expectedTotalTaxRounded = Math.round(expectedTotalTax * 100.0) / 100.0;

        ReceiptItem expected = new ReceiptItem(item, expectedTotalPriceRounded, expectedTotalTaxRounded);

        assertEquals(expected, receiptItem); // record equality works perfectly
    }

    @Test
    void testCheckoutWithMultipleQuantities() {
        Item item = new Item("imported chocolate", 10.00, 3, true, false); // quantity = 3
        cartService.addItem(item);

        when(taxCalculatorService.calculateTax(item)).thenReturn(0.50);

        List<ReceiptItem> receiptItems = cartService.checkout();

        assertEquals(1, receiptItems.size());
        ReceiptItem receiptItem = receiptItems.get(0);

        double totalTax = 3 * 0.50;
        double totalPrice = 3 * (10.00 + 0.50);
        double roundedTax = Math.round(totalTax * 100.0) / 100.0;
        double roundedTotal = Math.round(totalPrice * 100.0) / 100.0;

        assertEquals(new ReceiptItem(item, roundedTotal, roundedTax), receiptItem);
    }
}
