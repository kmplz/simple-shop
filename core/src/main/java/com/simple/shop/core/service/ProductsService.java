package com.simple.shop.core.service;

import com.simple.shop.core.dao.BrandsDao;
import com.simple.shop.core.dao.CategoriesDao;
import com.simple.shop.core.dao.ProductsDao;
import com.simple.shop.core.domain.Product;
import com.simple.shop.core.domain.ProductSave;
import com.simple.shop.core.domain.api.PageResponse;
import com.simple.shop.core.domain.filter.ProductsFilter;
import com.simple.shop.core.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductsDao dao;
    private final CategoriesDao categoriesDao;
    private final BrandsDao brandsDao;

    public PageResponse<Product> getByFilter(ProductsFilter filter) {
        return null;
    }

    @Transactional
    public void create(ProductSave product) {
        log.info("Creating product {}", product.getName());
        validate(product, true);
        dao.create(product);
    }

    @Transactional
    public void update(ProductSave product) {
        log.info("Updating product {}", product.getId());
        validate(product, false);
        dao.update(product);
    }

    public void delete(Integer id) {
        log.info("Deleting product {}", id);
        dao.delete(id);
    }

    private void validate(ProductSave product, Boolean isNew) {
        if (!categoriesDao.categoryExist(product.getCategoryId())) {
            throw new ServiceException.CategoryDoesNotExistException(product.getCategoryId());
        }
        if (!brandsDao.brandExist(product.getBrandId())) {
            throw new ServiceException.BrandDoesNotExistException(product.getBrandId());
        }
        if ((isNew && dao.nameExist(product.getName())) ||
                (!isNew && dao.nameExist(product.getName(), product.getId()))) {
            throw new ServiceException.ProductAlreadyExistException(product.getName());
        }
    }
}
