package com.example.modelfashion.Model.MHistory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ModelHistory implements Serializable {
    private String mIDHistory;
    private String mPhoneNumber;
    private String mAddress;
    private String mTimeOrder;
    private String mTimeRecieve;
    private String mStatus;
    private String mSumPrice;
    private List<ProductHistory> productHistoryList = new ArrayList<>();

    public ModelHistory(String mIDHistory, String mPhoneNumber, String mAddress, String mTimeOrder, String mTimeRecieve, String mStatus, String mSumPrice, List<ProductHistory> productHistoryList) {
        this.mIDHistory = mIDHistory;
        this.mPhoneNumber = mPhoneNumber;
        this.mAddress = mAddress;
        this.mTimeOrder = mTimeOrder;
        this.mTimeRecieve = mTimeRecieve;
        this.mStatus = mStatus;
        this.mSumPrice = mSumPrice;
        this.productHistoryList = productHistoryList;
    }

    public String getmIDHistory() {
        return mIDHistory;
    }

    public void setmIDHistory(String mIDHistory) {
        this.mIDHistory = mIDHistory;
    }

    public String getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmTimeOrder() {
        return mTimeOrder;
    }

    public void setmTimeOrder(String mTimeOrder) {
        this.mTimeOrder = mTimeOrder;
    }

    public String getmTimeRecieve() {
        return mTimeRecieve;
    }

    public void setmTimeRecieve(String mTimeRecieve) {
        this.mTimeRecieve = mTimeRecieve;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getmSumPrice() {
        return mSumPrice;
    }

    public void setmSumPrice(String mSumPrice) {
        this.mSumPrice = mSumPrice;
    }

    public List<ProductHistory> getProductHistoryList() {
        return productHistoryList;
    }

    public void setProductHistoryList(List<ProductHistory> productHistoryList) {
        this.productHistoryList = productHistoryList;
    }
}
