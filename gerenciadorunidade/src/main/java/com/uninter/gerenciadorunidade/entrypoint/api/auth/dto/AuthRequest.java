package com.uninter.gerenciadorunidade.entrypoint.api.auth.dto;

import jakarta.validation.constraints.Email;

public record AuthRequest(@Email String email, String password) {}
