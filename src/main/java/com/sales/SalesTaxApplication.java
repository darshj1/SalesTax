package com.sales;

import com.sales.model.Item;
import com.sales.model.ReceiptItem;
import com.sales.service.CartService;
import com.sales.service.ReceiptService;
import com.sales.service.TaxCalculatorService;
import com.sales.serviceImpl.CartServiceImpl;
import com.sales.serviceImpl.ReceiptPrinter;
import com.sales.serviceImpl.ReceiptServiceImpl;
import com.sales.serviceImpl.TaxCalculatorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Scanner;

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
