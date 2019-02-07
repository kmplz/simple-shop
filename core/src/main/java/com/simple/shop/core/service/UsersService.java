package com.simple.shop.core.service;

import com.simple.shop.core.dao.UsersDao;
import com.simple.shop.core.domain.User;
import com.simple.shop.core.domain.auth.Role;
import com.simple.shop.core.domain.auth.SignUpRequest;
import com.simple.shop.core.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersDao dao;
    private final BCryptPasswordEncoder passwordEncoder;

    public Optional<User> getByLogin(String login) {
        log.info("Getting user by login {}", login);
        return dao.byLogin(login);
    }

    @Transactional
    public void signUp(SignUpRequest request) {
        log.info("Registering user {}", request.getLogin());
        if (dao.loginExist(request.getLogin())) {
            throw new ServiceException.LoginExistsException("Account with login " + request.getLogin() + " already exists");
        }
        if (!dao.roleExist(request.getRoleId())) {
            throw new ServiceException.RoleDoesNotExistException("Role with id " + request.getRoleId() + " does not exist");
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        request.setLogin(request.getLogin().trim());
        request.setUsername(request.getUsername().trim());
        dao.save(request);
    }

    public List<Role> getRoles() {
        log.info("Getting roles");
        return dao.getRoles();
    }
}
