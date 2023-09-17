package com.example.shop.Additionals;

import com.example.shop.R;

public class Product {

    private String name;
    private int categoryId;

    private double price;

    private int imageId;

    private String description;
    public Product(String name, int categoryId, int imageId, double price, String description) {
        this.name = name;
        this.categoryId = categoryId;
        this.imageId = imageId;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getImageId() {
        return imageId;
    }

    public String getDescription() {
        return description;
    }
}
