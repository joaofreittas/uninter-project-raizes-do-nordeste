package com.uninter.gerenciadorunidade.controller.auth.dto;

import com.uninter.gerenciadorunidade.model.auth.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
    @NotBlank String name,
    @Email String email,
    @Size(min = 8) String password,
    Role role) {

}
