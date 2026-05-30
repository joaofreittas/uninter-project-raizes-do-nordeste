package com.uninter.gerenciadorunidade.controller.auth.dto;

import com.uninter.gerenciadorunidade.model.auth.Role;
import com.uninter.gerenciadorunidade.model.auth.User;
import lombok.Builder;

@Builder
public record UserResponse(
    Long id,
    String name,
    String email,
    Role role
) {

    public static UserResponse fromModel(final User userModel) {
        return UserResponse.builder()
            .id(userModel.getId())
            .name(userModel.getName())
            .email(userModel.getEmail())
            .role(userModel.getRole())
            .build();
    }

}
