package com.simple.shop.core.endpoint;

import com.simple.shop.core.domain.Category;
import com.simple.shop.core.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryEndpoint {

    private final CategoryService service;

    @GetMapping
    public List<Category> getAll() {
        return service.getAll();
    }
}
