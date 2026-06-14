package com.uninter.gerenciadorunidade.core.usecase.auth;

import com.uninter.gerenciadorunidade.core.domain.Role;
import com.uninter.gerenciadorunidade.core.domain.UserDomain;
import com.uninter.gerenciadorunidade.core.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserUseCase {

    private final UserGateway userGateway;
    private final PasswordEncoder passwordEncoder;

    public UserDomain execute(final String name, final String email,
                              final String rawPassword, final Role role) {
        var user = UserDomain.builder()
            .name(name)
            .email(email)
            .password(passwordEncoder.encode(rawPassword))
            .role(role == null ? Role.USER : role)
            .build();
        return userGateway.save(user);
    }

}
