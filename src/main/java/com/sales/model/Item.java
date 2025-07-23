package com.sales.model;

public record Item(String name, double price, boolean isImported, boolean isExempted, int quantity) {
}
