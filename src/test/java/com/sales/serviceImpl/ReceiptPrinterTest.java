package com.sales.serviceImpl;

import com.sales.model.Item;
import com.sales.model.Receipt;
import com.sales.model.ReceiptItem;
import org.junit.jupiter.api.*;
import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptPrinterTest {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void shouldPrintReceiptCorrectly() {
        Item item = new Item("book", 12.49, false, true, 1);
        ReceiptItem receiptItem = new ReceiptItem(item, 0.0);
        Receipt receipt = new Receipt(List.of(receiptItem));

        ReceiptPrinter printer = new ReceiptPrinter();
        printer.printReceipt(receipt);

        String printed = output.toString();
        assertTrue(printed.contains("1 book: 12.49"));
        assertTrue(printed.contains("Sales Taxes: 0.00"));
        assertTrue(printed.contains("Total: 12.49"));
    }
}
