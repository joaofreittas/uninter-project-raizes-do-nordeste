package com.uninter.gerenciadorunidade.entrypoint.api.auth.dto;

import com.uninter.gerenciadorunidade.core.domain.Role;
import com.uninter.gerenciadorunidade.core.domain.UserDomain;
import lombok.Builder;

@Builder
public record UserResponse(Long id, String name, String email, Role role) {

    public static UserResponse fromDomain(final UserDomain domain) {
        return UserResponse.builder()
            .id(domain.getId())
            .name(domain.getName())
            .email(domain.getEmail())
            .role(domain.getRole())
            .build();
    }

}
