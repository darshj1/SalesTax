package com.sales.service;

import com.sales.model.Item;
import org.springframework.stereotype.Component;

@Component
public interface TaxCalculatorService {
    Double calculateTax(Item item);
}
