package com.example.modelfashion.Model.MHistory;

import com.example.modelfashion.Model.response.my_product.MyProduct;

import java.util.ArrayList;

public class SubProduct {
    private String id;
    private ArrayList<MyProduct> myProducts = new ArrayList<>();

    public SubProduct(String id, ArrayList<MyProduct> myProducts) {
        this.id = id;
        this.myProducts = myProducts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<MyProduct> getMyProducts() {
        return myProducts;
    }

    public void setMyProducts(ArrayList<MyProduct> myProducts) {
        this.myProducts = myProducts;
    }
}
