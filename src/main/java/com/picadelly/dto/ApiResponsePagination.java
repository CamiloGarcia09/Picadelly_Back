package com.picadelly.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class ApiResponsePagination<T> {
    private boolean success;
    private String message;
    private Number totalPages;
    private Number pageNumber;
    private Number pageSize;
    private Number totalRecords;
    private T data;
}
