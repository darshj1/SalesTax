package com.sales.serviceImpl;

import com.sales.model.ReceiptItem;
import com.sales.service.CartService;
import com.sales.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    CartService cartService;

    @Override
    public void printReceipt(List<ReceiptItem> receiptItemList){
        receiptItemList.forEach(System.out::println);
        Double totalTax = receiptItemList.stream().mapToDouble(ReceiptItem::totalTax).sum();
        Double total = receiptItemList.stream().mapToDouble(ReceiptItem::totalPriceWithTax).sum();

        System.out.printf("Sales Taxes : %.2f%n",totalTax);
        System.out.printf("Total : %.2f%n",total);
    }
}
