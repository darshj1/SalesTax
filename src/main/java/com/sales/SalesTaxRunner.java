package com.sales;

import com.sales.model.Item;
import com.sales.model.ReceiptItem;
import com.sales.service.CartService;
import com.sales.service.ReceiptService;
import com.sales.service.TaxCalculatorService;
import com.sales.serviceImpl.ReceiptPrinter;

import java.util.List;

public class SalesTaxRunner {
    private final CartService cartService;
    private final TaxCalculatorService taxCalculator;
    private final ReceiptService receiptService;
    private final ReceiptPrinter printer;

    public SalesTaxRunner(CartService cartService,
                          TaxCalculatorService taxCalculator,
                          ReceiptService receiptService,
                          ReceiptPrinter printer) {
        this.cartService = cartService;
        this.taxCalculator = taxCalculator;
        this.receiptService = receiptService;
        this.printer = printer;
    }

    public void run() {
        List<Item> items = cartService.readItemsFromConsole();
        List<ReceiptItem> receiptItems = taxCalculator.calculateTaxes(items);
        var receipt = receiptService.generateReceipt(receiptItems);
        printer.printReceipt(receipt);
    }
}
