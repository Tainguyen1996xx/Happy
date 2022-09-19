package com.example.modelfashion.Model.response.product;

public class ProductResponse {
    private int code;
    private String msg;
    private ProductData data;

    public ProductResponse(int code, String msg, ProductData data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ProductResponse() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ProductData getData() {
        return data;
    }

    public void setData(ProductData data) {
        this.data = data;
    }
}
