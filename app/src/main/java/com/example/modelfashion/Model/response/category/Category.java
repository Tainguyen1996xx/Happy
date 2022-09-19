package com.example.modelfashion.Model.response.category;

public class Category {
    private String id;
    private String title;
    private int quantity;

    public Category(String id, String title, int quantity) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
    }

    public Category(String categoryName, int categoryAmount) {
        this.title = categoryName;
        this.quantity = categoryAmount;
    }

    public Category() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
