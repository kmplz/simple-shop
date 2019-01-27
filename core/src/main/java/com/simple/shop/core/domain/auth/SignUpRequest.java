package com.simple.shop.core.domain.auth;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class SignUpRequest {

    @Length(min = 5, max = 30)
    private String username;
    @Length(min = 5, max = 30)
    private String login;
    @Length(min = 6, max = 30)
    private String password;
    @NotNull
    private Integer roleId;
}
