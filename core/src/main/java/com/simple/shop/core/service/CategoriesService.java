package com.simple.shop.core.service;

import com.simple.shop.core.config.AppConfig;
import com.simple.shop.core.dao.CategoriesDao;
import com.simple.shop.core.domain.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoriesService {

    private final CategoriesDao dao;

    public List<Category> getAll() {
        log.info("Getting categories list");
        return dao.all();
    }
}
