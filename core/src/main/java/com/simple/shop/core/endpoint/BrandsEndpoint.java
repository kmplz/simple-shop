package com.simple.shop.core.endpoint;

import com.simple.shop.core.domain.Brand;
import com.simple.shop.core.domain.api.Response;
import com.simple.shop.core.service.BrandsService;
import com.simple.shop.core.validation.groups.Existing;
import com.simple.shop.core.validation.groups.New;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brands")
public class BrandsEndpoint {

    private final BrandsService service;

    @GetMapping
    public Response getAll() {
        return Response.ok(service.getAll());
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public Response create(@RequestBody @Validated(New.class) Brand brand) {
        return Response.from(() -> service.create(brand));
    }

    @PutMapping
    @Secured("ROLE_ADMIN")
    public Response update(@RequestBody @Validated(Existing.class) Brand brand) {
        return Response.from(() -> service.update(brand));
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public Response delete(@PathVariable Integer id) {
        return Response.from(() -> service.delete(id));
    }
}
