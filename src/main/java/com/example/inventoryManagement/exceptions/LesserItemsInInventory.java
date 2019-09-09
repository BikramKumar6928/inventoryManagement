package com.example.inventoryManagement.exceptions;

public class LesserItemsInInventory extends RuntimeException {
    public LesserItemsInInventory() {
        super("There are fewer items in stock");
    }

}
