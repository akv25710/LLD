package com.lld.InventoryManagementSystem;

public class Product {
    String id;
    Double price;
    Double weight; // in gms
    Size size;

    public Product(String id, Double price, Double weight, Size size) {
        this.id = id;
        this.price = price;
        this.weight = weight;
        this.size = size;
    }
}

enum Size {
    S, M, L
}
