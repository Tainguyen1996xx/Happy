package com.example.modelfashion.Model.response.product;

import java.util.List;

public class ProductData {
    private List<ProductPreview> results;
    private  int pageIndex;
    private  int pageSize;
    private  int totalPage;
    private  int totalItem;

    public ProductData(List<ProductPreview> results, int pageIndex, int pageSize, int totalPage, int totalItem) {
        this.results = results;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.totalItem = totalItem;
    }

    public ProductData() {
    }

    public List<ProductPreview> getResults() {
        return results;
    }

    public void setResults(List<ProductPreview> results) {
        this.results = results;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }
}
