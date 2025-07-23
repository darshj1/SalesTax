package com.sales.service;

import com.sales.model.Item;
import com.sales.model.ReceiptItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TaxCalculatorService {
    List<ReceiptItem> calculateTaxes(List<Item> items);
}
