package com.simple.shop.core.dao;

import com.simple.shop.core.domain.User;
import com.simple.shop.core.domain.auth.Role;
import com.simple.shop.core.domain.auth.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.singletonMap;

@Repository
@RequiredArgsConstructor
public class UsersDao {

    private final NamedParameterJdbcTemplate jdbc;

    public Optional<User> byLogin(String login) {
        String query = "SELECT u.*, r.name AS role FROM users u " +
                "LEFT JOIN roles r on u.role_id = r.id " +
                "WHERE u.login = :login";
        try {
            return Optional.ofNullable(
                    jdbc.queryForObject(query, singletonMap("login", login), new BeanPropertyRowMapper<>(User.class))
            );
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }

    public void save(SignUpRequest request) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username", request.getUsername());
        parameters.put("password_hash", request.getPassword());
        parameters.put("login", request.getLogin());
        parameters.put("role_id", request.getRoleId());
        new SimpleJdbcInsert(jdbc.getJdbcTemplate()).withTableName("users")
                .usingColumns(parameters.keySet().toArray(new String[0]))
                .execute(parameters);
    }

    public Boolean loginExist(String login) {
        return jdbc.queryForObject("SELECT count(1) > 0 from users WHERE login = :login",
                singletonMap("login", login), Boolean.class);
    }

    public Boolean roleExist(Integer roleId) {
        return jdbc.queryForObject("SELECT count(1) > 0 from roles WHERE id = :id",
                singletonMap("id", roleId), Boolean.class);
    }

    public List<Role> getRoles() {
        return jdbc.query(
                "SELECT * FROM roles ORDER BY id",
                new HashMap<>(), (rs, rowNum) -> new Role(rs.getInt("id"), rs.getString("name"))
        );
    }
}
