package com.example.modelfashion.Model.response.product;

public class ProductPreview {
    private String id;
    private String name;
    private double price;
    private String category;
    private String logoUrl;
    private String size;
    private int numberSave;
    private int quantity;

    public ProductPreview(String id, String name, double price, String category, String logoUrl, String size, int numberSave, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.logoUrl = logoUrl;
        this.size = size;
        this.numberSave = numberSave;
        this.quantity = quantity;
    }

    public ProductPreview() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getNumberSave() {
        return numberSave;
    }

    public void setNumberSave(int numberSave) {
        this.numberSave = numberSave;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
