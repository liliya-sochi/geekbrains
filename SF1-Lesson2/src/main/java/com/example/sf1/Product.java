package com.example.sf1;

public class Product {
    public static int counter = 0;
    private int id;
    private String title;
    private int cost;

    public Product(String title) {
        id = counter++;
        this.title = title;
        cost = 0;
    }

//    public Product(String title, int cost) {
//        id++;
//        this.title = title;
//        this.cost = cost;
//    }

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