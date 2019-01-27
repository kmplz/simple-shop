package com.simple.shop.core.domain.api;

import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T> {

    private List<T> items;
    private Integer total;
}
