package com.sales.serviceImpl;

import com.sales.model.Item;
import com.sales.model.ReceiptItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ReceiptServiceImplTests {
    @InjectMocks
    private ReceiptServiceImpl receiptService;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Redirect System.out
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testPrintReceipt() {
        // Arrange
        Item item1 = new Item("book", 12.49, 1, false, false);
        Item item2 = new Item("music CD", 14.99, 1, false, true);

        ReceiptItem receiptItem1 = new ReceiptItem(item1, 12.49, 0.00);
        ReceiptItem receiptItem2 = new ReceiptItem(item2, 16.49, 1.50);

        List<ReceiptItem> receiptItems = List.of(receiptItem1, receiptItem2);

        // Act
        receiptService.printReceipt(receiptItems);

        // Assert: check printed output
        String output = outContent.toString();
        assertTrue(output.contains("ReceiptItem{item="));
        assertTrue(output.contains("Sales Taxes : 1.50"));
        assertTrue(output.contains("Total : 28.98"));
    }
}
