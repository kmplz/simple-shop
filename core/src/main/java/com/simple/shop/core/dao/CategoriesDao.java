package com.simple.shop.core.dao;

import com.simple.shop.core.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import static com.simple.shop.core.util.ParameterUtils.getCommonParameters;
import static java.util.Collections.singletonMap;

@Repository
@RequiredArgsConstructor
public class CategoriesDao {

    private final NamedParameterJdbcTemplate jdbc;

    public List<Category> all() {
        String query = "SELECT * FROM categories WHERE active = TRUE ORDER BY name";
        return jdbc.query(query, new BeanPropertyRowMapper<>(Category.class));
    }

    public void create(Category category) {
        Map<String, Object> parameters = singletonMap("name", category.getName());
        new SimpleJdbcInsert(jdbc.getJdbcTemplate()).withTableName("categories")
                .usingColumns(parameters.keySet().toArray(new String[0]))
                .execute(parameters);
    }

    public void update(Category category) {
        String query = "UPDATE categories SET name = :name WHERE id = :id";
        jdbc.update(query, getCommonParameters(category.getId(), category.getName()));
    }

    public void delete(Integer id) {
        jdbc.update("UPDATE categories SET active = FALSE WHERE id = :id", singletonMap("id", id));
    }

    public Boolean nameExist(String name) {
        return jdbc.queryForObject("SELECT count(1) > 0 from categories WHERE name = :name AND active = TRUE",
                singletonMap("name", name), Boolean.class);
    }

    public Boolean nameExist(String name, Integer id) {
        String query = "SELECT count(1) > 0 from categories " +
                "WHERE name = :name AND id != :id AND active = TRUE";
        return jdbc.queryForObject(query, getCommonParameters(id, name), Boolean.class);
    }

    public Boolean categoryExist(Integer id) {
        return jdbc.queryForObject("SELECT count(1) > 0 from categories WHERE id = :id AND active = TRUE",
                singletonMap("id", id), Boolean.class);
    }
}
