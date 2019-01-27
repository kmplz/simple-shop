package com.simple.shop.core.service;

import com.simple.shop.core.dao.CategoriesDao;
import com.simple.shop.core.domain.Brand;
import com.simple.shop.core.domain.Category;
import com.simple.shop.core.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void create(Category category) {
        log.info("Creating category {}", category.getName());
        validate(category, true);
        dao.create(category);
    }

    @Transactional
    public void update(Category category) {
        log.info("Updating category {}", category.getName());
        validate(category, false);
        dao.update(category);
    }

    public void delete(Integer id) {
        log.info("Deleting category {}", id);
        dao.delete(id);
    }

    private void validate(Category category, Boolean isNew) {
        category.setName(category.getName().trim());
        if ((isNew && dao.nameExist(category.getName())) ||
                (!isNew && dao.nameExist(category.getName(), category.getId()))) {
            throw new ServiceException.CategoryAlreadyExistException(category.getName());
        }
    }
}
