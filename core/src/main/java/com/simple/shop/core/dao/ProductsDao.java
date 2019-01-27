package com.simple.shop.core.dao;

import com.simple.shop.core.domain.ProductSave;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

import static com.simple.shop.core.util.ParameterUtils.getCommonParameters;
import static java.util.Collections.singletonMap;

@Repository
@RequiredArgsConstructor
public class ProductsDao {

    private final NamedParameterJdbcTemplate jdbc;

    public void create(ProductSave product) {
        Map<String, Object> parameters = getParameters(product);
        new SimpleJdbcInsert(jdbc.getJdbcTemplate()).withTableName("products")
                .usingColumns(parameters.keySet().toArray(new String[0]))
                .execute(parameters);
    }

    public void update(ProductSave product) {
        String query = "UPDATE products " +
                "SET name = :name, description = :description, category_id = :category_id, brand_id = :brand_id " +
                "WHERE id = :id";
        Map<String, Object> parameters = getParameters(product);
        parameters.put("id", product.getId());
        jdbc.update(query, parameters);
    }

    public void delete(Integer id) {
        jdbc.update("UPDATE products SET active = FALSE WHERE id = :id", singletonMap("id", id));
    }

    public Boolean nameExist(String name) {
        return jdbc.queryForObject("SELECT count(1) > 0 from products WHERE name = :name AND active = TRUE",
                singletonMap("name", name), Boolean.class);
    }

    public Boolean nameExist(String name, Integer id) {
        String query = "SELECT count(1) > 0 from products " +
                "WHERE name = :name AND id != :id AND active = TRUE";
        return jdbc.queryForObject(query, getCommonParameters(id, name), Boolean.class);
    }

    private Map<String, Object> getParameters(ProductSave product) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", product.getName());
        parameters.put("description", product.getDescription());
        parameters.put("category_id", product.getCategoryId());
        parameters.put("brand_id", product.getBrandId());
        return parameters;
    }
}
