package com.uninter.gerenciadorunidade.core.input.auth;

import com.uninter.gerenciadorunidade.core.domain.user.Role;

public record RegisterUserInput(String name, String email, String rawPassword, Role role) {

}
