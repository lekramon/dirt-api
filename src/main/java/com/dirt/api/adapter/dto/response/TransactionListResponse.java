package com.dirt.api.adapter.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TransactionListResponse {
    @JsonProperty("size")
    private int size;

    @JsonProperty("total_size")
    private long totalSize;

    @JsonProperty("page")
    private int page;

    @JsonProperty("total_pages")
    private int totalPages;

    @JsonProperty("content")
    private List<TransactionResponse> transactionResponseList;

    public TransactionListResponse(int size, long totalSize, int page, int totalPages, List<TransactionResponse> transactionResponseList) {
        this.size = size;
        this.totalSize = totalSize;
        this.page = page;
        this.totalPages = totalPages;
        this.transactionResponseList = transactionResponseList;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<TransactionResponse> getTransactionResponseList() {
        return transactionResponseList;
    }

    public void setTransactionResponseList(List<TransactionResponse> transactionResponseList) {
        this.transactionResponseList = transactionResponseList;
    }
}
