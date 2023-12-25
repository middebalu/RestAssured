package main.java.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

public class GetContractAlerts {
    private int pageNumber;
    private int pageSize;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private TableFilters tableFilters;

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

    public TableFilters getTableFilters() {
        return tableFilters;
    }

    public void setTableFilters(TableFilters tableFilters) {
        this.tableFilters = tableFilters;
    }
}
