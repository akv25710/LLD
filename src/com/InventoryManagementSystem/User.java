package com.lld.InventoryManagementSystem;

public class User {
    public void addProduct() {
        InventorySystem.addProduct(new Product("1", 25.0d, 10.0d, Size.S));
    }

}
