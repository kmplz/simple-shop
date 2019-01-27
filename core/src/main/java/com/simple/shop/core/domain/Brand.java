package com.simple.shop.core.domain;

import com.simple.shop.core.validation.groups.Existing;
import com.simple.shop.core.validation.groups.New;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class Brand {

    @Null(groups = New.class)
    @NotNull(groups = Existing.class)
    private Integer id;
    @Length(min = 3, max = 20)
    private String name;
}
