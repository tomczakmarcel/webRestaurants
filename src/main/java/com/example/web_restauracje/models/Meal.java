package com.example.web_restauracje.models;

public class Meal {
    private double price;
    private String name;
    private String photoUrl;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private String category;

    public Meal(double price, String name, String photoUrl, String category) {
        this.price = price;
        this.name = name;
        this.photoUrl = photoUrl;
        this.category = category;
    }

    public Meal() {}

    public Meal(double price, String name, String photoUrl) {
        this.price = price;
        this.name = name;
        this.photoUrl = photoUrl;
    }
}