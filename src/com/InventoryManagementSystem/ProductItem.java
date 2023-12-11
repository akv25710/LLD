package com.lld.InventoryManagementSystem;

public class ProductItem {
    Integer id;
    String productId;
    ItemStatus status;
    String locationId;

    public ProductItem(Integer id, String productId, ItemStatus status) {
        this.id = id;
        this.productId = productId;
        this.status = status;
    }

    public void placeAtShelf(String locationId) {
        this.locationId = locationId;
    }

    public void removeFromShelf() {
        this.locationId = "";
    }
}


enum ItemStatus {
    Inventory, Transit, Delivery
}