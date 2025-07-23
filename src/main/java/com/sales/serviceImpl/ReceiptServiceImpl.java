package com.sales.serviceImpl;

import com.sales.model.Receipt;
import com.sales.model.ReceiptItem;
import com.sales.service.ReceiptService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService {
    @Override
    public Receipt generateReceipt(List<ReceiptItem> receiptItems) {
        return new Receipt(receiptItems);
    }
}