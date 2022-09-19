package com.example.modelfashion.Model.MHistory;

import java.util.ArrayList;

public class BillModel {
    private String bill_id;
    private String user_id;
    private String date_created;
    private String status;
    private String amount;
    private ArrayList<SubBillModel> bill_detail = new ArrayList<>();

    public BillModel(String bill_id, String user_id, String date_created, String status, String amount, ArrayList<SubBillModel> bill_detail) {
        this.bill_id = bill_id;
        this.user_id = user_id;
        this.date_created = date_created;
        this.status = status;
        this.amount = amount;
        this.bill_detail = bill_detail;
    }

    public String getBill_id() {
        return bill_id;
    }

    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public ArrayList<SubBillModel> getBill_detail() {
        return bill_detail;
    }

    public void setBill_detail(ArrayList<SubBillModel> bill_detail) {
        this.bill_detail = bill_detail;
    }
}
