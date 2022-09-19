package com.example.modelfashion.Model.response.category;

import java.util.List;

public class CategoryData {
    private List<Category> results;
    private  int pageIndex;
    private  int pageSize;
    private  int totalPage;
    private  int totalItem;

    public CategoryData() {
    }

    public CategoryData(List<Category> results, int pageIndex, int pageSize, int totalPage, int totalItem) {
        this.results = results;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.totalItem = totalItem;
    }

    public List<Category> getResults() {
        return results;
    }

    public void setResults(List<Category> results) {
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
