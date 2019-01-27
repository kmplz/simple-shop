package com.simple.shop.core.endpoint;

import com.simple.shop.core.domain.ProductSave;
import com.simple.shop.core.domain.api.Response;
import com.simple.shop.core.domain.filter.ProductsFilter;
import com.simple.shop.core.service.ProductsService;
import com.simple.shop.core.validation.groups.New;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductsEndpoint {

    private final ProductsService service;

    @PostMapping("/filtered")
    public Response getByFilter(@RequestBody @Valid ProductsFilter filter) {
        return Response.ok(service.getByFilter(filter));
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public Response create(@RequestBody @Validated(New.class) ProductSave product) {
        return Response.from(() -> service.create(product));
    }

    @PutMapping
    @Secured("ROLE_ADMIN")
    public Response update(@RequestBody @Validated(New.class) ProductSave product) {
        return Response.from(() -> service.update(product));
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public Response delete(@PathVariable Integer id) {
        return Response.from(() -> service.delete(id));
    }
}
