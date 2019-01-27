package com.simple.shop.core.domain;

import com.simple.shop.core.validation.groups.Existing;
import com.simple.shop.core.validation.groups.New;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

@Data
public class ProductSave {

    @Null(groups = New.class)
    @NotNull(groups = Existing.class)
    private Integer id;
    @Length(min = 5, max = 30)
    private String name;
    @Length(max = 256)
    private String description;
    @NotNull
    private Integer categoryId;
    @NotNull
    private Integer brandId;
    @NotNull
    @Positive
    private Double price;
}
