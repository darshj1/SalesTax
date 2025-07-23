package com.sales.serviceImpl;

import com.sales.model.Item;
import com.sales.model.ReceiptItem;
import com.sales.service.TaxCalculatorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaxCalculatorServiceImpl implements TaxCalculatorService {
    @Override
    public List<ReceiptItem> calculateTaxes(List<Item> items) {
        List<ReceiptItem> receiptItems = new ArrayList<>();
        for (Item item : items) {
            double tax = 0.0;
            if (!item.isExempted()) tax += item.price() * 0.10;
            if (item.isImported()) tax += item.price() * 0.05;
            tax = roundTax(tax);
            receiptItems.add(new ReceiptItem(item, tax));
        }
        return receiptItems;
    }

    private double roundTax(double tax) {
        return Math.ceil(tax * 20.0) / 20.0;
    }
}
