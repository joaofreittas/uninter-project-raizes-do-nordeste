package com.uninter.gerenciadorunidade.core.usecase.auth;

import com.uninter.gerenciadorunidade.core.domain.user.UserDomain;
import com.uninter.gerenciadorunidade.core.gateway.UserGateway;
import com.uninter.gerenciadorunidade.core.input.auth.RegisterUserInput;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserUseCase {

    private final UserGateway userGateway;
    private final PasswordEncoder passwordEncoder;

    public UserDomain execute(final RegisterUserInput input) {
        return userGateway.save(
            UserDomain.create(input.name(), input.email(),
                passwordEncoder.encode(input.rawPassword()), input.role()));
    }

}
