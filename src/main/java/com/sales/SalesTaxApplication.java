package com.sales;

import com.sales.model.Item;
import com.sales.model.ReceiptItem;
import com.sales.service.CartService;
import com.sales.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@ComponentScan(basePackages = "com.sales")
public class SalesTaxApplication {

	@Autowired
	CartService cartService;

	@Autowired
	ReceiptService receiptService;

	public static void main(String[] args) {
		getDetails();
	}

	public static void getDetails(){
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Enter items (e.g., '1 imported bottle of perfume at 27.99'), one per line:");
			System.out.println("Enter an empty line to finish input.\n");

			var context = SpringApplication.run(SalesTaxApplication.class);
			SalesTaxApplication app = context.getBean(SalesTaxApplication.class);
			app.getInputsFromUser(scanner);
		}
	}

	public void getInputsFromUser(Scanner scanner){
		//Reading All Inputs
		while (true) {
			String line = scanner.nextLine().trim();
			if (line.isEmpty()) break;

			try {
				Item item = parseItem(line);
				//Parsing Input and storing it into ITEM Object
				cartService.addItem(item);
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid input format. Please try again.");
			}
		}

		//Printing Receipt after Tax Calculation
		System.out.println("\nReceipt:");
		List<ReceiptItem> receiptItemList = cartService.checkout();
		receiptService.printReceipt(receiptItemList);
	}

	// Parses lines like: "1 imported box of chocolates at 10.00"
	private static Item parseItem(String line) {
		if (!line.contains(" at "))
			throw new IllegalArgumentException("Missing 'at' delimiter.");

		//Getting Price From Inputs
		String[] parts = line.split(" at ");
		String pricePart = parts[1];
		Double price = Double.parseDouble(pricePart);

		//Getting Quantity From Inputs
		String[] tokens = parts[0].split(" ", 2);
		Integer quantity = Integer.parseInt(tokens[0]);
		String name = tokens[1];

		//Check whether item is imported and not exempt
		Boolean isImported = name.contains("imported");
		Boolean isExempt = isExemptProduct(name);

		return new Item(name, price, quantity, isImported, isExempt);
	}

	// Exemption based on keywords in Problem-Solving Statement
	private static boolean isExemptProduct(String name) {
		String lower = name.toLowerCase();
		return lower.contains("book") || lower.contains("chocolate") || lower.contains("pills");
	}

}
