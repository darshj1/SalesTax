package com.sales;

import com.sales.service.CartService;
import com.sales.service.ReceiptService;
import com.sales.service.TaxCalculatorService;
import com.sales.serviceImpl.CartServiceImpl;
import com.sales.serviceImpl.ReceiptPrinter;
import com.sales.serviceImpl.ReceiptServiceImpl;
import com.sales.serviceImpl.TaxCalculatorServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.sales")
public class SalesTaxApplication {

	public static void main(String[] args) {
		CartService cartService = new CartServiceImpl();
		TaxCalculatorService taxCalculator = new TaxCalculatorServiceImpl();
		ReceiptService receiptService = new ReceiptServiceImpl();
		ReceiptPrinter printer = new ReceiptPrinter();

		new SalesTaxRunner(cartService, taxCalculator, receiptService, printer).run();
	}
}
