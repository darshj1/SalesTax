package com.sales.serviceImpl;

import com.sales.model.Item;
import com.sales.service.CartService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CartServiceImpl implements CartService {
    private static final List<String> EXEMPT_KEYWORDS = List.of("book", "chocolate","chocolates", "pill");

    @Override
    public List<Item> readItemsFromConsole() {
        Scanner scanner = new Scanner(System.in);
        List<Item> items = new ArrayList<>();
        int count = 0;

        try{
            System.out.println("Enter number of items:");
            count = Integer.parseInt(scanner.nextLine());
        }catch (NumberFormatException e){
            System.out.println("Please Enter valid Input ");
        }

        for (int i = 0; i < count; i++) {
            System.out.println("Enter item (e.g., '1 imported box of chocolates at 10.00'):");
            String line = scanner.nextLine().trim();

            boolean isImported = line.contains("imported");
            int atIndex = line.lastIndexOf(" at ");
            double price = Double.parseDouble(line.substring(atIndex + 4).trim());

            String nameAndQty = line.substring(0, atIndex).trim();
            String[] parts = nameAndQty.split(" ", 2);
            int quantity = Integer.parseInt(parts[0]);
            String name = parts[1];

            boolean isExempted = EXEMPT_KEYWORDS.stream().anyMatch(name.toLowerCase()::contains);

            items.add(new Item(name, price, isImported, isExempted, quantity));
        }

        return items;
    }
}