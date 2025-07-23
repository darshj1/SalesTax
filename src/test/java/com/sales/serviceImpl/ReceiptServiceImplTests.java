package com.sales.serviceImpl;

import com.sales.model.Receipt;
import com.sales.model.ReceiptItem;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class ReceiptServiceImplTests {
    @Mock
    ReceiptItem item1;

    @Mock
    ReceiptItem item2;

    @InjectMocks
    ReceiptServiceImpl receiptService;

    @Test
    void shouldGenerateReceiptCorrectly() {
        when(item1.getTotalTax()).thenReturn(1.50);
        when(item1.getTotalPriceWithTax()).thenReturn(11.50);
        when(item2.getTotalTax()).thenReturn(0.00);
        when(item2.getTotalPriceWithTax()).thenReturn(12.49);

        Receipt receipt = receiptService.generateReceipt(List.of(item1, item2));

        assertEquals(2, receipt.items().size());
        assertEquals(1.50, receipt.getTotalSalesTax(), 0.01);
        assertEquals(23.99, receipt.getTotalAmount(), 0.01);
    }
}
