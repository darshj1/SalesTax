package com.sales.service;

import com.sales.model.Receipt;
import com.sales.model.ReceiptItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReceiptService {
    Receipt generateReceipt(List<ReceiptItem> receiptItems);
}
