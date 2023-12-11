package com.lld.InventoryManagementSystem;

public class Shelf {
    String id;
    ShelfStatus status;
    ShelfSize size;

    public Shelf(String id, ShelfStatus status, ShelfSize size) {
        this.id = id;
        this.size = size;
        this.status = status;
    }

}

enum ShelfStatus {
    EMPTY, BOOKED, OCCUPIED
}

enum ShelfSize {
    S, M, L
}
