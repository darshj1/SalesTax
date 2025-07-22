package com.sales;

import com.sales.model.Item;
import com.sales.model.ReceiptItem;
import com.sales.service.CartService;
import com.sales.service.ReceiptService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class SalesTaxApplicationTests {

	private SalesTaxApplication app;
	private CartService cartService;
	private ReceiptService receiptService;

	@BeforeEach
	void setUp() {
		app = new SalesTaxApplication();

		cartService = Mockito.mock(CartService.class);
		receiptService = Mockito.mock(ReceiptService.class);

		app.cartService = cartService;
		app.receiptService = receiptService;
	}
	@Test
	void testParseItem_ImportedExempt() {
		String line = "1 imported box of chocolates at 10.00";
		Item item = invokeParseItem(line);

		assertEquals("imported box of chocolates", item.getName());
		assertEquals(1, item.getQuantity());
		assertEquals(10.00, item.getPrice());
		assertTrue(item.getImported());
		assertTrue(item.getExempt());
	}

	@Test
	void testParseItem_NonImportedTaxable() {
		String line = "2 music CDs at 14.99";
		Item item = invokeParseItem(line);

		assertEquals("music CDs", item.getName());
		assertEquals(2, item.getQuantity());
		assertEquals(14.99, item.getPrice());
		assertFalse(item.getImported());
		assertFalse(item.getExempt());
	}

	@Test
	void testParseItem_ImportedTaxable() {
		String line = "1 imported bottle of perfume at 47.50";
		Item item = invokeParseItem(line);

		assertEquals("imported bottle of perfume", item.getName());
		assertEquals(1, item.getQuantity());
		assertEquals(47.50, item.getPrice());
		assertTrue(item.getImported());
		assertFalse(item.getExempt());
	}

	@Test
	void testParseItem_InvalidFormatThrowsException() {
		String line = "1 music CD 14.99";
		assertThrows(IllegalArgumentException.class, () -> invokeParseItem(line));
	}

	// Helper method to access private static method
	private Item invokeParseItem(String line) {
		try {
			Method method = SalesTaxApplication.class.getDeclaredMethod("parseItem", String.class);
			method.setAccessible(true);
			return (Item) method.invoke(null, line);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Test
	void testGetInputsFromUser_validAndInvalidInputs() {
		String simulatedInput = String.join("\n",
				"1 book at 12.49",
				"1 imported perfume at 47.50\n",
				""
		);

		Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));

		// Stub checkout to return mock data
		List<ReceiptItem> mockReceipt = List.of(
				new ReceiptItem(new Item("book", 12.49, 1, false, true), 12.49, 0.0),
				new ReceiptItem(new Item("imported perfume", 47.50, 1, true, false), 54.63, 7.13)
		);
		Mockito.when(cartService.checkout()).thenReturn(mockReceipt);

		// Run method
		app.getInputsFromUser(scanner);

		// Verify valid items added
		Mockito.verify(cartService, Mockito.times(2)).addItem(Mockito.any(Item.class));

		// Verify checkout and receipt printed
		Mockito.verify(cartService).checkout();
		Mockito.verify(receiptService).printReceipt(mockReceipt);
	}
}
