package com.uninter.gerenciadorunidade.entrypoint.api.auth;

import com.uninter.gerenciadorunidade.core.usecase.auth.AuthenticateUserUseCase;
import com.uninter.gerenciadorunidade.core.usecase.auth.RegisterUserUseCase;
import com.uninter.gerenciadorunidade.entrypoint.api.auth.dto.AuthRequest;
import com.uninter.gerenciadorunidade.entrypoint.api.auth.dto.AuthResponse;
import com.uninter.gerenciadorunidade.entrypoint.api.auth.dto.RegisterRequest;
import com.uninter.gerenciadorunidade.entrypoint.api.auth.dto.UserResponse;
import com.uninter.gerenciadorunidade.config.auth.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthFacade {

    private final RegisterUserUseCase registerUserUseCase;
    private final AuthenticateUserUseCase authenticateUserUseCase;
    private final JWTService jwtService;

    public UserResponse register(final RegisterRequest request) {
        var domain = registerUserUseCase.execute(
            request.name(), request.email(), request.password(), request.role());
        return UserResponse.fromDomain(domain);
    }

    public AuthResponse authenticate(final AuthRequest request) {
        var token = authenticateUserUseCase.execute(
            request.email(), request.password(), jwtService::generateToken);
        return new AuthResponse(token);
    }

}
