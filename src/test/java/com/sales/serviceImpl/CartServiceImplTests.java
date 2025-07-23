package com.sales.serviceImpl;

import com.sales.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartServiceImplTests {
    private CartServiceImpl cartService;

    @BeforeEach
    void setUp() {
        cartService = new CartServiceImpl();
    }

    @Test
    void shouldParseInputCorrectly() {
        String input = "1\n1 imported box of chocolates at 10.00\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        List<Item> items = cartService.readItemsFromConsole();
        assertEquals(1, items.size());
        Item item = items.get(0);

        assertEquals("imported box of chocolates", item.name());
        assertEquals(10.00, item.price());
        assertTrue(item.isImported());
        assertTrue(item.isExempted());
        assertEquals(1, item.quantity());
    }
}