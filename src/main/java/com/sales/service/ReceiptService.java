package com.sales.service;

import com.sales.model.ReceiptItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReceiptService {
    void printReceipt(List<ReceiptItem> receiptItemList);
}
