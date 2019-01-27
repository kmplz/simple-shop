package com.simple.shop.core.service;

import com.simple.shop.core.dao.BrandsDao;
import com.simple.shop.core.domain.Brand;
import com.simple.shop.core.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrandsService {

    private final BrandsDao dao;

    public List<Brand> getAll() {
        log.info("Getting brands list");
        return dao.all();
    }

    @Transactional
    public void create(Brand brand) {
        log.info("Creating brand {}", brand.getName());
        validate(brand, true);
        dao.create(brand);
    }

    @Transactional
    public void update(Brand brand) {
        log.info("Updating brand {}", brand.getId());
        validate(brand, false);
        dao.update(brand);
    }

    public void delete(Integer id) {
        log.info("Deleting brand {}", id);
        dao.delete(id);
    }

    private void validate(Brand brand, Boolean isNew) {
        brand.setName(brand.getName().trim());
        if ((isNew && dao.nameExist(brand.getName())) ||
                (!isNew && dao.nameExist(brand.getName(), brand.getId()))) {
            throw new ServiceException.BrandAlreadyExistException(brand.getName());
        }
    }
}
