package com.lld.InventoryManagementSystem;

import java.util.*;

public class InventorySystem {

    static Map<String, Product> products;
    static Map<Integer, Shelf>  shelves;
    static Map<Size, List<ShelfSize>> sizeMap;

    public InventorySystem() {
        products = new HashMap<>();
        shelves = new HashMap<>();
        sizeMap = new HashMap<>();
        sizeMap.put(Size.S, List.of(ShelfSize.S));
        sizeMap.put(Size.M, List.of(ShelfSize.M));
        sizeMap.put(Size.L, List.of(ShelfSize.L));
    }

    public static List<Product> getProducts() {
        return new ArrayList<>(products.values());
    }

    public Product getProduct(String productId) {
        return products.get(productId);
    }

    public static void addProduct(Product product) {
        products.put(product.id, product);
    }

    public List<Shelf> getFreeShelves() {
        return new ArrayList<>(shelves.values());
    }

    public void placeItem(ProductItem item) {
        var product = getProduct(item.productId);
        var shelf = getFreeShelf(product.size);
        item.placeAtShelf(shelf.id);
    }

    private Shelf getFreeShelf(Size size) {
        var shelfSize = sizeMap.get(size).get(0);
        return new Shelf(UUID.randomUUID().toString(), ShelfStatus.BOOKED, shelfSize);
    }

    public void removeItem(ProductItem item) {
        item.removeFromShelf();
    }

}

