package com.example.mock.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "login не должен быть пустым")
    private String login;

    @NotBlank(message = "password не должен быть пустым")
    private String password;

}
