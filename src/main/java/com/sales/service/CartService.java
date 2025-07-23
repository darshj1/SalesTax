package com.sales.service;

import com.sales.model.Item;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CartService {
    List<Item> readItemsFromConsole();
}
