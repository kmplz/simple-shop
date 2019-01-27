package com.simple.shop.core.dao;

import com.simple.shop.core.domain.Brand;
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
public class BrandsDao {

    private final NamedParameterJdbcTemplate jdbc;

    public List<Brand> all() {
        String query = "SELECT * FROM brands WHERE active = TRUE ORDER BY name";
        return jdbc.query(query, new BeanPropertyRowMapper<>(Brand.class));
    }

    public void create(Brand brand) {
        Map<String, Object> parameters = singletonMap("name", brand.getName());
        new SimpleJdbcInsert(jdbc.getJdbcTemplate()).withTableName("brands")
                .usingColumns(parameters.keySet().toArray(new String[0]))
                .execute(parameters);
    }

    public void update(Brand brand) {
        String query = "UPDATE brands SET name = :name WHERE id = :id";
        jdbc.update(query, getCommonParameters(brand.getId(), brand.getName()));
    }

    public void delete(Integer id) {
        jdbc.update("UPDATE brands SET active = FALSE WHERE id = :id", singletonMap("id", id));
    }

    public Boolean nameExist(String name) {
        return jdbc.queryForObject("SELECT count(1) > 0 from brands WHERE name = :name AND active = TRUE",
                singletonMap("name", name), Boolean.class);
    }

    public Boolean nameExist(String name, Integer id) {
        String query = "SELECT count(1) > 0 from brands " +
                "WHERE name = :name AND id != :id AND active = TRUE";
        return jdbc.queryForObject(query, getCommonParameters(id, name), Boolean.class);
    }

    public Boolean brandExist(Integer id) {
        return jdbc.queryForObject("SELECT count(1) > 0 from brands WHERE id = :id AND active = TRUE",
                singletonMap("id", id), Boolean.class);
    }
}
