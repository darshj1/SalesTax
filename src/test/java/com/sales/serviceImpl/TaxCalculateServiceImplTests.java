package com.sales.serviceImpl;

import com.sales.model.Item;
import com.sales.service.TaxCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaxCalculateServiceImplTests {

    private TaxCalculatorService taxCalculatorService;

    @BeforeEach
    void setUp() {
        taxCalculatorService = new TaxCalculatorServiceImpl();
    }

    @Test
    void testCalculateTax_ExemptAndNotImported() {
        Item item = new Item("book", 12.49, 1, false, true); // exempt and not imported
        double tax = taxCalculatorService.calculateTax(item);
        assertEquals(0.0, tax, 0.001);
    }

    @Test
    void testCalculateTax_TaxableOnly() {
        Item item = new Item("music CD", 14.99, 1, false, false); // taxable
        double tax = taxCalculatorService.calculateTax(item);
        double expectedTax = 14.99 * 0.10;
        assertEquals(expectedTax, tax, 0.001);
    }

    @Test
    void testCalculateTax_ImportedOnly() {
        Item item = new Item("imported chocolate", 10.00, 1, true, true); // imported only
        double tax = 10.00 * 0.05;
        assertEquals(tax, taxCalculatorService.calculateTax(item), 0.001);
    }

    @Test
    void testCalculateTax_TaxableAndImported() {
        Item item = new Item("imported perfume", 47.50, 1, true, false); // taxable + imported
        assertEquals(7.15, taxCalculatorService.calculateTax(item), 0.001);
    }
}
