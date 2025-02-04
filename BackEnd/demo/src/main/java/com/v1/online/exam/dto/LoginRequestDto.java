package com.v1.online.exam.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {

    @NotBlank(message = "Username is required")
    private String name;

    @NotBlank(message = "Password is required")
    private String password;
}
