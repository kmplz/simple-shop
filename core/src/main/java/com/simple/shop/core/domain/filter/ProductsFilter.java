package com.simple.shop.core.domain.filter;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ProductsFilter {

    private String name;
    @NotNull
    private List<Integer> brands;
    @NotNull
    private Integer page;
    @NotNull
    @Min(10)
    @Max(100)
    private Integer itemsPerPage;
}
