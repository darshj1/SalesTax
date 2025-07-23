package com.sales;

import com.sales.model.Item;
import com.sales.model.Receipt;
import com.sales.model.ReceiptItem;
import com.sales.service.CartService;
import com.sales.service.ReceiptService;
import com.sales.service.TaxCalculatorService;
import com.sales.serviceImpl.ReceiptPrinter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SalesTaxRunnerTest {

	@Mock
	CartService cartService;

	@Mock
	TaxCalculatorService taxCalculator;

	@Mock
	ReceiptService receiptService;

	@Mock
	ReceiptPrinter printer;

	@Test
	void testRunExecutesFlowCorrectly() {
		Item item = new Item("book", 12.49, false, true, 1);
		ReceiptItem receiptItem = new ReceiptItem(item, 0.0);
		Receipt receipt = new Receipt(List.of(receiptItem));

		when(cartService.readItemsFromConsole()).thenReturn(List.of(item));
		when(taxCalculator.calculateTaxes(List.of(item))).thenReturn(List.of(receiptItem));
		when(receiptService.generateReceipt(List.of(receiptItem))).thenReturn(receipt);

		SalesTaxRunner runner = new SalesTaxRunner(cartService, taxCalculator, receiptService, printer);
		runner.run();

		verify(cartService).readItemsFromConsole();
		verify(taxCalculator).calculateTaxes(List.of(item));
		verify(receiptService).generateReceipt(List.of(receiptItem));
		verify(printer).printReceipt(receipt);
	}
}
