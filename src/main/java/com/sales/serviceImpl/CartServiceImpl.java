package com.sales.serviceImpl;

import com.sales.model.Item;
import com.sales.model.ReceiptItem;
import com.sales.service.CartService;
import com.sales.service.TaxCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    TaxCalculatorService taxCalculatorService;

    public List<Item> items = new ArrayList<>();

    @Override
    public void addItem(Item item){
        items.add(item);
    }

    @Override
    public List<ReceiptItem> checkout(){
        return items.stream()
                .map(item ->{
                    Double tax = taxCalculatorService.calculateTax(item);
                    Double totalPrice = item.getPrice()+tax;
                    System.out.println("Item : "+item.getPrice()+" "+tax+" "+totalPrice);
                    return new ReceiptItem(item,roundOff(item.getQuantity()*totalPrice),roundOff(item.getQuantity()*tax));
                }).toList();
    }

    private static Double roundOff(Double value){
        return Math.round(value*100.0)/100.0;
    }
}
