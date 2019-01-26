package com.simple.shop.core.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Category {

    private Long id;
    private String name;
}
