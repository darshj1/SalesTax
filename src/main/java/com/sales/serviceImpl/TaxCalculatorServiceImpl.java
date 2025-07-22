package com.sales.serviceImpl;

import com.sales.model.Item;
import com.sales.service.TaxCalculatorService;
import org.springframework.stereotype.Service;

@Service
public class TaxCalculatorServiceImpl implements TaxCalculatorService {
    private static final Double BASIC_TAX_RATE=0.10;
    private static final Double IMPORT_DUTY_RATE=0.05;

    @Override
    public Double calculateTax(Item item){
        double tax =0.0;
        if(!item.getExempt()){
            tax  = tax+(item.getPrice()*BASIC_TAX_RATE);
        }

        if(item.getImported()){
            tax  = tax+(item.getPrice()*IMPORT_DUTY_RATE);
        }
        return tax;
    }
}
