package com.simple.shop.core.dao;

import com.simple.shop.core.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryDao {

    private final NamedParameterJdbcTemplate jdbc;

    public List<Category> all() {
        String query = "SELECT * FROM categories WHERE active = TRUE ORDER BY name";
        return jdbc.query(query, this::mapRow);
    }

    private Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Category.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .build();
    }
}
