package com.example.modelfashion.Model;

public class Product {
    private int productId;
    private String productName;
    private String productDesc;
    private String productPrice;
    private String productAvatarUrl;
    private String productType;
    private int productImg;

    public Product() {
    }

    public Product(int productId, String productName, String productDesc, String productPrice, String productAvatarUrl, String productType, int productImg) {
        this.productId = productId;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productPrice = productPrice;
        this.productAvatarUrl = productAvatarUrl;
        this.productType = productType;
        this.productImg = productImg;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductAvatarUrl() {
        return productAvatarUrl;
    }

    public void setProductAvatarUrl(String productAvatarUrl) {
        this.productAvatarUrl = productAvatarUrl;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getProductImg() {
        return productImg;
    }

    public void setProductImg(int productImg) {
        this.productImg = productImg;
    }
}
