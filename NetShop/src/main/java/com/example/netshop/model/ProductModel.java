package com.example.netshop.model;

public class ProductModel {
    private int id;
    private String title;
    private int cost;

    public ProductModel(int id, String title) {
        this.id = id;
        this.title = title;
        cost = 0;
    }

    public ProductModel(int id, String title, int cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Продукт (" + id + ") " + title + " = " + cost + "$.";
    }
}