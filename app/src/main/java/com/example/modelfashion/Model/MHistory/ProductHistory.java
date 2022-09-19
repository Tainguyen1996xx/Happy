package com.example.modelfashion.Model.MHistory;

public class ProductHistory {
    private String mID;
    private String mNameProduct;
    private String mPriceProduct;
    private String mSizeProduct;
    private String mImgeProduct;
    private String mSumProduct;


    public ProductHistory(String mID, String mNameProduct, String mPriceProduct, String mSizeProduct, String mImgeProduct, String mSumProduct) {
        this.mID = mID;
        this.mNameProduct = mNameProduct;
        this.mPriceProduct = mPriceProduct;
        this.mSizeProduct = mSizeProduct;
        this.mImgeProduct = mImgeProduct;
        this.mSumProduct = mSumProduct;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmNameProduct() {
        return mNameProduct;
    }

    public void setmNameProduct(String mNameProduct) {
        this.mNameProduct = mNameProduct;
    }

    public String getmPriceProduct() {
        return mPriceProduct;
    }

    public void setmPriceProduct(String mPriceProduct) {
        this.mPriceProduct = mPriceProduct;
    }

    public String getmSizeProduct() {
        return mSizeProduct;
    }

    public void setmSizeProduct(String mSizeProduct) {
        this.mSizeProduct = mSizeProduct;
    }

    public String getmImgeProduct() {
        return mImgeProduct;
    }

    public void setmImgeProduct(String mImgeProduct) {
        this.mImgeProduct = mImgeProduct;
    }

    public String getmSumProduct() {
        return mSumProduct;
    }

    public void setmSumProduct(String mSumProduct) {
        this.mSumProduct = mSumProduct;
    }
}
