package main.java.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ContractAlertsResponse {
    @JsonProperty("totalCount")
    private int totalCount;
    private int pageNumber;
    private int pageSize;
    private List<Data> data;

    public int getData1() {
        return totalCount;
    }

    public void setData1(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

}
